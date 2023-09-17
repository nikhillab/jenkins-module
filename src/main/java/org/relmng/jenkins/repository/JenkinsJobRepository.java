/**
 * 
 */
package org.relmng.jenkins.repository;

import java.util.List;

import org.relmng.jenkins.model.JenkinsJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author nikhil
 *
 */
public interface JenkinsJobRepository extends JpaRepository<JenkinsJob, Long> {

	/**
	 * @param jobsIds
	 */
	@Query("UPDATE JenkinsJob SET isActive = false WHERE pkJobId IN (:jobsIds)")
	public abstract void markJobAsInActive(@Param("jobsIds") List<Long> jobsIds);

}
