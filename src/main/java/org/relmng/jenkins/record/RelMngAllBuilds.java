package org.relmng.jenkins.record;

import java.util.List;

import com.offbytwo.jenkins.model.Build;

public record RelMngAllBuilds(String _class, List<Build> allBuilds) {

}
