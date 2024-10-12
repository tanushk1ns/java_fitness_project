package src;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import src.exceptions.RecordNotFoundException;
import src.repositories.FitnessRepository;
import src.services.FitnessService;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestDeleteRecordExceptionCase {
    @Mock
    private FitnessRepository fitnessRepository;

    @InjectMocks
    private FitnessService fitnessService;

    @Test
    public void testDeleteRecordException() {

        given(fitnessRepository.findName("Ann")).willReturn(null);

        RecordNotFoundException ex = assertThrows(
                RecordNotFoundException.class,
                () -> fitnessService.deleteRecord("Ann"));
        assertEquals("A person named Ann is not registered for the class", ex.getMessage());

        verify(fitnessRepository, never()).deleteRecord("Ann");
    }
}
