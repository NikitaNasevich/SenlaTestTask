CREATE TABLE Location
(
    location_id          bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name                 varchar(170) NOT NULL,
    region               varchar(255) NOT NULL,
    country              varchar(100) NOT NULL,
    latitude             decimal CHECK (latitude >= -90 AND longitude <=90),
    longitude            decimal CHECK (longitude >= -180 AND longitude <= 180),
    time_zone            varchar(100) NOT NULL,
    unix_local_date_time bigint CHECK (unix_local_date_time > 0),
    local_date_time      timestamp NOT NULL
);

CREATE TABLE Condition
(
    condition_id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    text         varchar(100) NOT NULL,
    icon_link    varchar(255) NOT NULL,
    code         int CHECK (code >= 1000 AND code <= 9999)
);

CREATE TABLE Current
(
    current_id                 bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    unix_last_update_date_time bigint CHECK (unix_last_update_date_time > 0),
    last_update_date_time      timestamp NOT NULL,
    temp_c                     decimal,
    temp_f                     decimal,
    is_day                     bool NOT NULL,
    condition_id               int REFERENCES Condition (condition_id),
    wind_speed_mph             decimal,
    wind_speed_kph             decimal,
    wind_direction_degree      int CHECK (wind_direction_degree >= 0 AND wind_direction_degree <= 360),
    wind_direction_compass     varchar(3),
    pressure_mb                decimal,
    pressure_inch              decimal,
    precipitation_mm           decimal,
    precipitation_inch         decimal,
    humidity                   int CHECK (humidity >= 0 AND humidity <= 100),
    cloud_cover_percent        int CHECK (cloud_cover_percent >= 0 AND cloud_cover_percent <= 100),
    feels_like_temp_c          decimal,
    feels_like_temp_f          decimal,
    visibility_km              decimal,
    visibility_miles           decimal,
    uv_index                   decimal,
    wind_gust_mph              decimal,
    wind_gust_kph              decimal
);

CREATE TABLE Weather
(
    weather_id  bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    location_id int REFERENCES Location (location_id),
    current_id  int references Current (current_id),
    saved_at    timestamp NOT NULL
);