package org.openjdk.btrace.instr;

import org.openjdk.btrace.compiler.PackGenerator;
import org.openjdk.btrace.core.SharedSettings;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class InstrPackGenerator implements PackGenerator {
    @Override
    public byte[] generateProbePack(byte[] classData) throws IOException {
        BTraceProbeNode bpn = (BTraceProbeNode) new BTraceProbeFactory(SharedSettings.GLOBAL.GLOBAL).createProbe(classData);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(bos)) {
            BTraceProbePersisted bpp = BTraceProbePersisted.from(bpn);
            bpp.write(dos);
        }

        return bos.toByteArray();
    }
}
