package pbouda.jfr.cgroups.streaming;

import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordingStream;
import pbouda.jfr.cgroups.CgroupsRecorder;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Streaming {

    public static void main(String[] args) throws InterruptedException {
        startStreaming();
        CgroupsRecorder.start();
        Thread.currentThread().join();
    }

    private static void startStreaming() {
        RecordingStream rs = new RecordingStream();
        Runtime.getRuntime().addShutdownHook(new Thread(rs::close));

        // Event configuration
        rs.enable("cgroups.CfsSettings")
                .with("period", "beginChunk");
        rs.enable("cgroups.CpuStat")
                .withPeriod(Duration.ofSeconds(1));
        rs.enable("process.ContextSwitches")
                .withPeriod(Duration.ofSeconds(1));
//        rs.enable("jdk.CPULoad").withPeriod(duration);
//        rs.enable("jdk.YoungGarbageCollection").withoutThreshold();
//        rs.enable("jdk.OldGarbageCollection").withoutThreshold();
//        rs.enable("jdk.GCHeapSummary").withPeriod(duration);
//        rs.enable("jdk.PhysicalMemory").withPeriod(duration);
//        rs.enable("jdk.GCConfiguration").withPeriod(duration);
//        rs.enable("jdk.SafepointBegin");
//        rs.enable("jdk.SafepointEnd");
//        rs.enable("jdk.ObjectAllocationOutsideTLAB").withStackTrace();
//        rs.enable("jdk.ObjectAllocationInNewTLAB").withStackTrace();
//        rs.enable("jdk.ExecutionSample").withPeriod(Duration.ofMillis(10)).withStackTrace();
//        rs.enable("jdk.JavaThreadStatistics").withPeriod(duration);
//        rs.enable("jdk.ClassLoadingStatistics").withPeriod(duration);
//        rs.enable("jdk.Compilation").withoutThreshold();
//        rs.enable("jdk.GCHeapConfiguration").withPeriod(duration);
//        rs.enable("jdk.Flush").withoutThreshold();

        // Dispatch handlers
        rs.onEvent("cgroups.CfsSettings", Streaming::printCfsSettings);
        rs.onEvent("cgroups.CpuStat", Streaming::printCpuStat);
        rs.onEvent("process.ContextSwitches", Streaming::printContextSwitches);
//        rs.onEvent("jdk.CPULoad", Main::onCPULoad);
//        rs.onEvent("jdk.YoungGarbageCollection", Main::onYoungColletion);
//        rs.onEvent("jdk.OldGarbageCollection", Main::onOldCollection);
//        rs.onEvent("jdk.GCHeapSummary", Main::onGCHeapSummary);
//        rs.onEvent("jdk.PhysicalMemory", Main::onPhysicalMemory);
//        rs.onEvent("jdk.GCConfiguration", Main::onGCConfiguration);
//        rs.onEvent("jdk.SafepointBegin", Main::onSafepointBegin);
//        rs.onEvent("jdk.SafepointEnd", Main::onSafepointEnd);
//        rs.onEvent("jdk.ObjectAllocationOutsideTLAB", Main::onObjectAllocationOutsideTLAB);
//        rs.onEvent("jdk.ObjectAllocationInNewTLAB", Main::onObjectAllocationInNewTLAB);
//        rs.onEvent("jdk.ExecutionSample", Main::onExecutionSample);
//        rs.onEvent("jdk.JavaThreadStatistics", Main::onJavaThreadStatistics);
//        rs.onEvent("jdk.ClassLoadingStatistics", Main::onClassLoadingStatistics);
//        rs.onEvent("jdk.Compilation", Main::onCompilation);
//        rs.onEvent("jdk.GCHeapConfiguration", Main::onGCHeapConfiguration);
//        rs.onEvent("jdk.Flush", Main::onFlushpoint);
//        rs.onFlush(Main::printReport);
        rs.startAsync();

    }

    private static void printContextSwitches(RecordedEvent event) {
        long voluntary = event.getLong("voluntary");
        long nonVoluntary = event.getLong("nonVoluntary");

        System.out.println(
                "------------------------\n" +
                " VOLUNTARY: " + voluntary + "\n" +
                " NON_VOLUNTARY: " + nonVoluntary + "\n" +
                "------------------------\n");
    }

    private static void printCfsSettings(RecordedEvent event) {
        long quotaDuration = event.getLong("quotaDuration");
        long periodDuration = event.getLong("periodDuration");

        System.out.println(
                "------------------------\n" +
                " QUOTA: " + quotaDuration + "\n" +
                " PERIOD: " + periodDuration + "\n" +
                "------------------------\n");
    }

    private static void printCpuStat(RecordedEvent event) {
        long periods = event.getLong("periods");
        long throttledPeriods = event.getLong("throttledPeriods");
        Duration throttledTime = event.getDuration("throttledTime");
        Instant startTime = event.getStartTime();
        Instant endTime = event.getEndTime();

        LocalDateTime ldt = LocalDateTime.ofInstant(startTime, ZoneOffset.UTC);
        LocalDateTime edt = LocalDateTime.ofInstant(endTime, ZoneOffset.UTC);

        System.out.printf("[%s/%s: %d, throttled: %d, throttled-time: %d]\n",
                ldt, edt, periods, throttledPeriods, throttledTime.toMillis() * 1000);
    }
}