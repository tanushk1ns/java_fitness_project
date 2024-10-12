package src;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import src.dto.RecordRequest;
import src.exceptions.MaxCapacityException;
import src.repositories.FitnessRepository;
import src.services.FitnessService;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestAddRecordExceptionCase {
    @Mock
    private FitnessRepository fitnessRepository;

    @InjectMocks
    private FitnessService fitnessService;

    @Test
    public void testAddRecordException() {
        RecordRequest recordRequest = new RecordRequest();
        recordRequest.setName("Ann");
        recordRequest.setSchedule_id(1);

        given(fitnessRepository.countByScheduleId(recordRequest.getSchedule_id())).willReturn(1);
        given(fitnessRepository.getCapacity(recordRequest.getSchedule_id())).willReturn(1);
        MaxCapacityException ex = assertThrows(MaxCapacityException.class, () -> fitnessService.addRecord("Alice", 1));
        assertEquals("Please choose another time, this class is fully booked.", ex.getMessage());
        verify(fitnessRepository, never()).addRecord(any().toString(),1);
    }
}
