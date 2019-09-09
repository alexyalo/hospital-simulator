package delivery;

import java.util.Objects;

public class PatientsReportResponseDTO {
    private final int healthyCount;
    private final int deadCount;
    private final int tuberculosisCount;
    private final int feverCount;
    private final int diabetesCount;

    private PatientsReportResponseDTO(Builder builder) {
        this.healthyCount = builder.healthyCount;
        this.deadCount = builder.deadCount;
        this.tuberculosisCount = builder.tuberculosisCount;
        this.feverCount = builder.feverCount;
        this.diabetesCount = builder.diabetesCount;
    }

    public String getResponse() {
        return String.format("F:%s,H:%s,D:%s,T:%s,X:%s", feverCount, healthyCount,
                diabetesCount, tuberculosisCount, deadCount);
    }

    public static class Builder {

        private int healthyCount;
        private int deadCount;
        private int tuberculosisCount;
        private int diabetesCount;
        private int feverCount;

        public Builder() {
            healthyCount = 0;
            deadCount = 0;
            tuberculosisCount = 0;
            diabetesCount = 0;
            feverCount = 0;
        }

        public Builder withHealthy(int count) {
            healthyCount = count;
            return this;
        }

        public Builder withDead(int count) {
            deadCount = count;
            return this;
        }

        public Builder withTuberculosis(int count) {
            tuberculosisCount = count;
            return this;
        }

        public Builder withFever(int count) {
            feverCount = count;
            return this;
        }

        public Builder withDiabetes(int count) {
            diabetesCount = count;
            return this;
        }

        public PatientsReportResponseDTO build() {
            return new PatientsReportResponseDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientsReportResponseDTO)) return false;
        PatientsReportResponseDTO that = (PatientsReportResponseDTO) o;
        return healthyCount == that.healthyCount &&
                deadCount == that.deadCount &&
                tuberculosisCount == that.tuberculosisCount &&
                feverCount == that.feverCount &&
                diabetesCount == that.diabetesCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(healthyCount, deadCount, tuberculosisCount, feverCount, diabetesCount);
    }

    @Override
    public String toString() {
        return "PatientsReportResponseDTO{" +
                "healthyCount=" + healthyCount +
                ", deadCount=" + deadCount +
                ", tuberculosisCount=" + tuberculosisCount +
                ", feverCount=" + feverCount +
                ", diabetesCount=" + diabetesCount +
                '}';
    }
}
