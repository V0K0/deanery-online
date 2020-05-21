CREATE INDEX st_group_indx ON student(group_id);

CREATE INDEX gr_plan_indx ON university_group(plan_id);

CREATE INDEX gr_spec_indx ON university_group(specialty_id);

CREATE INDEX sch_subject_group_indx ON schedule(subject_id, group_id);

CREATE INDEX sub_plan_indx ON subject(plan_id);

