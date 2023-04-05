package com.example.helloworld.worker;
import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zengxc
 */
@Component

public class PutProductWorker implements Worker {
    Map<String, Integer> cart = new HashMap<>();
        private final String taskDefName = "putProduct";


    @Override
    public String getTaskDefName() {
        return taskDefName;
    }

    @Override
    public TaskResult execute(Task task) {
        cart.clear();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //System.out.printf("WorkflowInstanceId: %s Executing: %s in:%s%n",task.getWorkflowInstanceId(), getTaskDefName(),task.getInputData().get("products"));
        List<String> products = (List<String>) task.getInputData().get("products");

        for(String product:products){
            if(cart.containsKey(product)){
                cart.put(product.toString(),cart.get(product)+1);
            }
            else{
                cart.put(product.toString(),1);
            }
        }

        TaskResult result = new TaskResult(task);
        result.setStatus(TaskResult.Status.COMPLETED);

        //Register the output of the task
        //result.getOutputData().put("data", resultMap);
        result.getOutputData().put("cart",cart);
        return result;
    }

}
