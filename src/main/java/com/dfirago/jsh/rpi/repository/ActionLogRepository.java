package com.dfirago.jsh.rpi.repository;

import com.dfirago.jsh.rpi.domain.ActionLogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dmfi on 13/01/2017.
 */
public interface ActionLogRepository extends JpaRepository<ActionLogEntry, Long> {

}
