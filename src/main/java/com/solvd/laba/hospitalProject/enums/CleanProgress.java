package com.solvd.laba.hospitalProject.enums;

public enum CleanProgress {
        NOT_STARTED("Not started"),
        IN_PROGRESS("In progress"),
        COMPLETED("Cleaning completed");

        private final String progressMessage;

        CleanProgress(String progressMessage) {
            this.progressMessage = progressMessage;
        }

        public String getProgressMessage() {
            return progressMessage;
        }
    }

