package fr.cyberdodo.cronduler.job;

import fr.cyberdodo.cronduler.service.TacheExecutionService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DispatchJob implements Job {
    @Autowired
    private TacheExecutionService execService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Long tacheId = context.getMergedJobDataMap().getLong("tacheId");
        try {
            execService.runTache(tacheId);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}