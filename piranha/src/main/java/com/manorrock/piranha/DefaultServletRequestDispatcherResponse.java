/*
 *  Copyright (c) 2002-2018, Manorrock.com. All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *      1. Redistributions of source code must retain the above copyright
 *         notice, this list of conditions and the following disclaimer.
 *
 *      2. Redistributions in binary form must reproduce the above copyright
 *         notice, this list of conditions and the following disclaimer in the
 *         documentation and/or other materials provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY OF SUCH DAMAGE.
 */
package com.manorrock.piranha;

/**
 * The default ServletRequestDispatcherResponse.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class DefaultServletRequestDispatcherResponse extends WebApplicationResponse {

    /**
     * Constructor.
     */
    public DefaultServletRequestDispatcherResponse() {
        super();
        this.outputStream = new DefaultServletRequestDispatcherOutputStream();
    }

    /**
     * Get the buffer size.
     *
     * @return the buffer size.
     */
    @Override
    public int getBufferSize() {
        return 0;
    }

    /**
     * Get the bytes in the buffer.
     *
     * @return the bytes in the buffer.
     */
    public byte[] getResponseBody() {
        if (this.gotWriter) {
            this.writer.flush();
        }
        DefaultServletRequestDispatcherOutputStream output = (DefaultServletRequestDispatcherOutputStream) this.outputStream;
        return output.getBytes();
    }

    /**
     * Reset the buffer.
     */
    @Override
    public void resetBuffer() {
        verifyNotCommitted("resetBuffer");
        DefaultServletRequestDispatcherOutputStream output = (DefaultServletRequestDispatcherOutputStream) this.outputStream;
        output.reset();
    }

    /**
     * Set the buffer size.
     *
     * @param size the buffer size.
     */
    @Override
    public void setBufferSize(int size) {
    }
}
