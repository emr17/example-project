package com.example.helloworld.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import org.springframework.stereotype.Component;

/**
 * @author zengxc
 */
@Component
public class BillWorker implements Worker {

    private final String taskDefName="bill";

    @Override
    public String getTaskDefName() {
        return taskDefName;
    }

    @Override
    public TaskResult execute(Task task) {
        System.out.printf("WorkflowInstanceId: %s Executing: %s in:%s%n",task.getWorkflowInstanceId(), getTaskDefName(),task.getInputData().get("sum"));
        System.out.println("Sum is :" + task.getInputData().get("sum"));

        TaskResult result = new TaskResult(task);
        result.setStatus(TaskResult.Status.COMPLETED);
        result.getOutputData().put("final", "Sum is :" + task.getInputData().get("sum"));
        return result;
    }
}
