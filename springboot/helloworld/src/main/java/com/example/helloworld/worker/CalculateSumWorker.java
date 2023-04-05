package com.example.helloworld.worker;
import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zengxc
 */
@Component
public class CalculateSumWorker implements Worker {
    Map<String, Integer> prodPrice = new HashMap<>();
        private final String taskDefName = "calculateSum";

    public CalculateSumWorker() {
        prodPrice.put("bread", 5);
        prodPrice.put("egg", 20);
    }

    @Override
    public String getTaskDefName() {
        return taskDefName;
    }

    @Override
    public TaskResult execute(Task task) {

        //System.out.printf("WorkflowInstanceId: %s Executing: %s in:%s%n",task.getWorkflowInstanceId(), getTaskDefName(),task.getInputData().get("cart"));
        Map<String, Object> cart = (Map<String, Object>) task.getInputData().get("cart");
        int sum = 0;
        int price =0;
        for (String key : cart.keySet()) {
            price= prodPrice.get(key);
            sum += price*((Integer)cart.get(key));
        }

        TaskResult result = new TaskResult(task);
        result.setStatus(TaskResult.Status.COMPLETED);

        //Register the output of the task
        //result.getOutputData().put("data", resultMap);
        result.getOutputData().put("sum", sum);
        return result;
    }

}
