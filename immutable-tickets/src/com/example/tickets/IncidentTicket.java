package com.example.tickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * INTENTION: A ticket should be an immutable record-like object.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - mutable fields
 * - multiple constructors
 * - public setters
 * - tags list can be modified from outside
 * - validation is scattered elsewhere
 *
 * TODO (student): refactor to immutable + Builder.
 */
public final class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;
    private final String description;
    private final String priority;
    private final List<String> tags;
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;
    private final String source;

    private IncidentTicket(Builder b) {
        this.id = b.id;
        this.reporterEmail = b.reporterEmail;
        this.title = b.title;
        this.description = b.description;
        this.priority = b.priority;
        this.tags = Collections.unmodifiableList(new ArrayList<>(b.tags));
        this.assigneeEmail = b.assigneeEmail;
        this.customerVisible = b.customerVisible;
        this.slaMinutes = b.slaMinutes;
        this.source = b.source;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getReporterEmail() {
        return reporterEmail;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public List<String> getTags() {
        return tags;
    }  // unmodifiable

    public String getAssigneeEmail() {
        return assigneeEmail;
    }

    public boolean isCustomerVisible() {
        return customerVisible;
    }

    public Integer getSlaMinutes() {
        return slaMinutes;
    }

    public String getSource() {
        return source;
    }

    public Builder toBuilder() {
        return new Builder(id, reporterEmail, title)
                .description(description)
                .priority(priority)
                .tags(tags)
                .assigneeEmail(assigneeEmail)
                .customerVisible(customerVisible)
                .slaMinutes(slaMinutes)
                .source(source);
    }

    public static Builder builder(String id, String reporterEmail, String title) {
        return new Builder(id, reporterEmail, title);
    }

    @Override
    public String toString() {
        return "IncidentTicket{"
                + "id='" + id + '\''
                + ", reporterEmail='" + reporterEmail + '\''
                + ", title='" + title + '\''
                + ", description='" + description + '\''
                + ", priority='" + priority + '\''
                + ", tags=" + tags
                + ", assigneeEmail='" + assigneeEmail + '\''
                + ", customerVisible=" + customerVisible
                + ", slaMinutes=" + slaMinutes
                + ", source='" + source + '\''
                + '}';
    }

    public static final class Builder {

        // Required
        private final String id;
        private final String reporterEmail;
        private final String title;

        // Optional
        private String description;
        private String priority;
        private List<String> tags = new ArrayList<>();
        private String assigneeEmail;
        private boolean customerVisible;
        private Integer slaMinutes;
        private String source;

        public Builder(String id, String reporterEmail, String title) {
            this.id = id;
            this.reporterEmail = reporterEmail;
            this.title = title;
        }

        public Builder description(String v) {
            this.description = v;
            return this;
        }

        public Builder priority(String v) {
            this.priority = v;
            return this;
        }

        public Builder tags(List<String> v) {
            this.tags = new ArrayList<>(v);
            return this;
        }

        public Builder addTag(String tag) {
            this.tags.add(tag);
            return this;
        }

        public Builder assigneeEmail(String v) {
            this.assigneeEmail = v;
            return this;
        }

        public Builder customerVisible(boolean v) {
            this.customerVisible = v;
            return this;
        }

        public Builder slaMinutes(Integer v) {
            this.slaMinutes = v;
            return this;
        }

        public Builder source(String v) {
            this.source = v;
            return this;
        }

        /**
         * All validation lives here.
         */
        public IncidentTicket build() {
            Validation.requireTicketId(id);
            Validation.requireEmail(reporterEmail, "reporterEmail");
            Validation.requireNonBlank(title, "title");
            Validation.requireMaxLen(title, 80, "title");
            Validation.requireOneOf(priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");
            if (assigneeEmail != null) {
                Validation.requireEmail(assigneeEmail, "assigneeEmail");
            }
            Validation.requireRange(slaMinutes, 5, 7200, "slaMinutes");
            return new IncidentTicket(this);
        }
    }
}
