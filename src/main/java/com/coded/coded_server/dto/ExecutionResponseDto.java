import java.util.List;

import lombok.Data;
import java.util.List;

@Data
public class ExecutionResponseDto {
    private String submissionId;
    private Boolean success;
    private String message;
    private double score;
    private String output;
    private String error;
    private List<TestCaseResultRequestDto> testCaseResults;

}