package be.btep.teamcappucino.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Act {
    private LocalDateTime startTime;
    private Duration duration;
    private Talent talent;

    public Act(LocalDateTime startTime, Duration duration, Talent talent) {
        this.startTime = startTime;
        this.duration = duration;
        this.talent = talent;
    }

    public Act(LocalDateTime of) {
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Talent getTalent() {
        return talent;
    }

    public void setTalent(Talent talent) {
        this.talent = talent;
    }
}
