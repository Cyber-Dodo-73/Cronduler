package fr.cyberdodo.cronduler.service;

import fr.cyberdodo.cronduler.entity.JourFerie;
import fr.cyberdodo.cronduler.entity.Tache;
import fr.cyberdodo.cronduler.repository.JourFerieRepository;
import fr.cyberdodo.cronduler.repository.TacheRepository;
import fr.cyberdodo.cronduler.job.DispatchJob;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.quartz.impl.calendar.HolidayCalendar;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class SchedulerService {
    private final Scheduler scheduler;
    private final TacheRepository tacheRepo;
    private final JourFerieRepository jfRepo;

    @PostConstruct
    public void rescheduleAll() throws SchedulerException {
        scheduler.clear();
        HolidayCalendar cal = new HolidayCalendar();
        for (JourFerie jf : jfRepo.findAll()) {
            cal.addExcludedDate(Date.from(
                    jf.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        scheduler.addCalendar("joursFeries", cal, true, true);

        for (Tache t : tacheRepo.findByActifTrue()) {
            JobDetail job = JobBuilder.newJob(DispatchJob.class)
                    .withIdentity("job_" + t.getId())
                    .usingJobData("tacheId", t.getId())
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger_" + t.getId())
                    .withSchedule(CronScheduleBuilder.cronSchedule(t.getCronExpression())
                            .withMisfireHandlingInstructionDoNothing())
                    .modifiedByCalendar("joursFeries")
                    .build();

            scheduler.scheduleJob(job, trigger);
        }
    }
}