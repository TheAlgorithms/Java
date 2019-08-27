package com.designpatterns.creational.builder;

/**
 * The Builder is a design pattern designed to provide a flexible solution to various object creation problems in
 * object-oriented programming. The intent of the Builder design pattern is to separate the construction of a complex
 * object from its representation.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Builder_pattern">Builder Pattern</a>
 */

public class Desktop {
    private String CPU;
    private String RAM;

    private boolean isGraphicCardEnabled;
    private String operatingSystem;
    private int diskSizeGB;
    private String graphicCard;

    private Desktop(DesktopBuilder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.isGraphicCardEnabled = builder.isGraphicCardEnabled;
        this.operatingSystem = builder.operatingSystem;
        this.diskSizeGB = builder.diskSizeGB;
        this.graphicCard = builder.graphicCard;
    }

    /**
     * Builder class for the above Desktop class. Constructs the Desktop by invoking the Desktop class constructor and
     * allows access to set optional fields in the Desktop class.
     */
    public static class DesktopBuilder {
        private String CPU;
        private String RAM;
        private boolean isGraphicCardEnabled;
        private String operatingSystem;
        private int diskSizeGB;
        private String graphicCard;

        public DesktopBuilder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }

        public DesktopBuilder setGraphicCardEnabled(boolean graphicCardEnabled) {
            this.isGraphicCardEnabled = graphicCardEnabled;
            return this;
        }

        public DesktopBuilder setOperatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        public DesktopBuilder setDiskSizeGB(int diskSize) {
            this.diskSizeGB = diskSize;
            return this;
        }

        public DesktopBuilder setGraphicCard(String graphicCard) {
            this.graphicCard = graphicCard;
            return this;
        }

        public Desktop build() {
            return new Desktop(this);
        }
    }

    @Override
    public String toString() {
        return "Desktop{" +
                "CPU='" + CPU + '\'' +
                ", RAM='" + RAM + '\'' +
                ", isGraphicCardEnabled=" + isGraphicCardEnabled +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", diskSizeGB=" + diskSizeGB +
                ", graphicCard='" + graphicCard + '\'' +
                '}';
    }
}
