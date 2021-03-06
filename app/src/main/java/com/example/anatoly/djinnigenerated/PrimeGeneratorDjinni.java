// AUTOGENERATED FILE - DO NOT MODIFY!
// This file generated by Djinni from prime.djinni

package com.example.anatoly.djinnigenerated;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/** definitition of the C++ interface to be called from Java */
public abstract class PrimeGeneratorDjinni {
    public abstract ArrayList<Integer> generatePrime(int amount);

    public static PrimeGeneratorDjinni create()
    {
        return CppProxy.create();
    }

    private static final class CppProxy extends PrimeGeneratorDjinni
    {
        private final long nativeRef;
        private final AtomicBoolean destroyed = new AtomicBoolean(false);

        private CppProxy(long nativeRef)
        {
            if (nativeRef == 0) throw new RuntimeException("nativeRef is zero");
            this.nativeRef = nativeRef;
        }

        private native void nativeDestroy(long nativeRef);
        public void _djinni_private_destroy()
        {
            boolean destroyed = this.destroyed.getAndSet(true);
            if (!destroyed) nativeDestroy(this.nativeRef);
        }
        protected void finalize() throws java.lang.Throwable
        {
            _djinni_private_destroy();
            super.finalize();
        }

        @Override
        public ArrayList<Integer> generatePrime(int amount)
        {
            assert !this.destroyed.get() : "trying to use a destroyed object";
            return native_generatePrime(this.nativeRef, amount);
        }
        private native ArrayList<Integer> native_generatePrime(long _nativeRef, int amount);

        public static native PrimeGeneratorDjinni create();
    }
}
