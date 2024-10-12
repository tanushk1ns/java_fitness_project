package src.services;

import org.springframework.stereotype.Service;
import src.exceptions.MaxCapacityException;
import src.exceptions.RecordNotFoundException;
import src.models.Registrations;
import src.repositories.FitnessRepository;

@Service
public class FitnessService {

    private final FitnessRepository fitnessRepository;

    public FitnessService(FitnessRepository fitnessRepository) {
        this.fitnessRepository = fitnessRepository;
    }

    public void addRecord(String name, long schedule_id){
        if (fitnessRepository.countByScheduleId(schedule_id) < fitnessRepository.getCapacity(schedule_id)) {
            fitnessRepository.addRecord(name, schedule_id);
        }
        else {
            throw new MaxCapacityException();
        }
    }

    public void deleteRecord(String name){
        if (fitnessRepository.findName(name) != null) {
            fitnessRepository.deleteRecord(name);
        }
        else {
            throw new RecordNotFoundException(name);
        }
    }

    public Iterable<Registrations> getAllRecords() {
        return fitnessRepository.findAll();
    }
}
