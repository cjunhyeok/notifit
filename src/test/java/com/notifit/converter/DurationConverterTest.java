package com.notifit.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;

class DurationConverterTest {

    @Test
    @DisplayName("Duration 타입의 1시간을 Long 타입의 3600초로 변환한다.")
    void durationToLongTest() {
        // given
        Duration oneDayDuration = Duration.ofHours(1);
        Long oneDaySeconds = 3600L;
        DurationConverter converter = new DurationConverter();

        // when
        Long convertedSeconds = converter.convertToDatabaseColumn(oneDayDuration);

        // then
        assertThat(convertedSeconds).isEqualTo(oneDaySeconds);
    }

    @Test
    @DisplayName("Long 타입의 3600초를 Duration 타입의 1시간으로 변환한다.")
    void LongToDurationTest() {
        // given
        Long oneDaySeconds = 3600L;
        Duration oneDayDuration = Duration.ofHours(1);
        DurationConverter converter = new DurationConverter();

        // when
        Duration convertedDuration = converter.convertToEntityAttribute(oneDaySeconds);

        // then
        assertThat(convertedDuration).isEqualTo(oneDayDuration);
    }
}