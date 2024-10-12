package src.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import src.models.Registrations;


public interface FitnessRepository extends CrudRepository<Registrations, Long> {
    @Modifying
    @Query("INSERT INTO Registrations(name, schedule_id) VALUES(:name, :schedule_id)")
    void addRecord(String name, long schedule_id);

    @Query("SELECT COUNT(*) FROM Registrations WHERE schedule_id = :schedule_id")
    int countByScheduleId(long schedule_id);

    @Query("SELECT capacity FROM Classes JOIN Schedule ON Schedule.class_id = Classes.id WHERE Schedule.id = :schedule_id")
    int getCapacity(long schedule_id);

    @Modifying
    @Query("DELETE FROM Registrations WHERE name = :name")
    void deleteRecord(String name);

    @Query("SELECT name FROM Registrations WHERE name LIKE :name")
    String findName(String name);
}
