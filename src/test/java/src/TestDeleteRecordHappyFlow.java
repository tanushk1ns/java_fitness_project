package src;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import src.dto.RecordRequest;
import src.repositories.FitnessRepository;
import src.services.FitnessService;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class TestDeleteRecordHappyFlow {
    @Mock
    private FitnessRepository fitnessRepository;

    @InjectMocks
    private FitnessService fitnessService;

    @Test
    public void testDeleteRecordHappyFlow() {
        RecordRequest recordRequest = new RecordRequest();
        recordRequest.setName("Ann");
        recordRequest.setSchedule_id(4);

        given(fitnessRepository.findName(recordRequest.getName())).willReturn(String.valueOf(Optional.of(recordRequest)));
        fitnessService.deleteRecord("Ann");

        verify(fitnessRepository, times(1)).findName("Ann");
        verify(fitnessRepository, times(1)).deleteRecord("Ann");
    }
}
