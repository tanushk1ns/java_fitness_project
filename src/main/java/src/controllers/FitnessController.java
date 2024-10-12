package src.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.dto.RecordRequest;
import src.exceptions.MaxCapacityException;
import src.exceptions.RecordNotFoundException;
import src.models.Registrations;
import src.services.FitnessService;

import java.util.logging.Logger;

@RestController
public class FitnessController {
    private final Logger logger = Logger.getLogger(FitnessController.class.getName());


    private final FitnessService fitnessService;

    public FitnessController(FitnessService fitnessService) {
        this.fitnessService = fitnessService;
    }

    @PostMapping("/record")
    public ResponseEntity<String> addRecord(@RequestBody RecordRequest recordRequest){
        logger.info(String.valueOf(recordRequest));
        fitnessService.addRecord(recordRequest.getName(), recordRequest.getSchedule_id());
        logger.info(recordRequest.getName() + recordRequest.getSchedule_id());
        ResponseEntity<String> response = new ResponseEntity<>("Succesfully!", HttpStatus.OK);
        return response;
    }

    @ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    @ExceptionHandler(MaxCapacityException.class)
    public String handleException(MaxCapacityException exception) {
        return exception.getMessage();
    }

    @GetMapping("/registrations")
    public Iterable<Registrations> getAllRecords(){
        return fitnessService.getAllRecords();
    }

    @DeleteMapping("/cancel/{name}")
    public ResponseEntity deleteRecord(@PathVariable String name){
        fitnessService.deleteRecord(name);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public String handleException(RecordNotFoundException exception) {
        return exception.getMessage();
    }

}
