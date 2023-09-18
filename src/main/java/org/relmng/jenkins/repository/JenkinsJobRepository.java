/**
 * 
 */
package org.relmng.jenkins.repository;

import java.util.List;

import org.relmng.jenkins.model.JenkinsJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nikhil
 *
 */
public interface JenkinsJobRepository extends JpaRepository<JenkinsJob, Long> {

	/**
	 * @param jobsIds
	 */
	@Modifying
	@Transactional
	@Query("UPDATE JenkinsJob SET isActive = false WHERE pkJobId IN (:jobsIds)")
	public abstract void markJobAsInActive(@Param("jobsIds") List<Long> jobsIds);
	
	
	public abstract List<JenkinsJob> findByJenkinsServerDetailsId(Long jenkinsServerDetailsId);

}
