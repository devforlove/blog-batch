package blog.study.top.job.blog.스프링배치flow;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StepNextJobConfiguration {

	@Bean
	Job stepNextJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder("stepNextJob", jobRepository)
				.start(step1(jobRepository, transactionManager))
				.next(step2(jobRepository, transactionManager))
				.next(step3(jobRepository, transactionManager))
				.build();
	}

	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("step1", jobRepository)
				.tasklet((contribution, chunkContext) -> {
					log.info(">>>>> This is Step1");
					return RepeatStatus.FINISHED;
				}, transactionManager)
				.build();
	}

	@Bean
	public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("step2", jobRepository)
				.tasklet((contribution, chunkContext) -> {
					log.info(">>>>> This is Step2");
					return RepeatStatus.FINISHED;
				}, transactionManager)
				.build();
	}

	@Bean
	public Step step3(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("step3", jobRepository)
				.tasklet((contribution, chunkContext) -> {
					log.info(">>>>>> This is Step3");
					return RepeatStatus.FINISHED;
				}, transactionManager)
				.build();
	}
}
