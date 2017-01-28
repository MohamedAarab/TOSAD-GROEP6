package task;

import domain.targetDatabase.ITargetDatabaseService;
import domain.targetDatabase.TargetDatabaseServiceImp;

/**
 * Created by lucas on 27-1-2017.
 */
public class ExecuteController implements IExecuteController {
    @Override
    public String executeScript(String host, String triggerCode) {
        ITargetDatabaseService targetDatabaseService = TargetDatabaseServiceImp.getInstance();
        return targetDatabaseService.executeScript(host, triggerCode);
    }
}
