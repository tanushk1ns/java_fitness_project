package src;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import src.dto.RecordRequest;
import src.repositories.FitnessRepository;
import src.services.FitnessService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class TestAddRecordHappyFlow {
    @Mock
    private FitnessRepository fitnessRepository;

    @InjectMocks
    private FitnessService fitnessService;

    @Test
    public void testAddRecordHappyFlow() {
        RecordRequest recordRequest = new RecordRequest();
        recordRequest.setName("Ann");
        recordRequest.setSchedule_id(1);
    given(fitnessRepository.countByScheduleId(recordRequest.getSchedule_id())).willReturn(1);
    given(fitnessRepository.getCapacity(recordRequest.getSchedule_id())).willReturn(2);
    fitnessService.addRecord("Alice", 1);
    verify(fitnessRepository).countByScheduleId(recordRequest.getSchedule_id());
    verify(fitnessRepository).getCapacity(recordRequest.getSchedule_id());
    verify(fitnessRepository).addRecord("Alice", 1);
    }
}
