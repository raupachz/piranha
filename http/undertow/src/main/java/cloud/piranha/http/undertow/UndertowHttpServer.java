/*
 * Copyright (c) 2002-2020 Manorrock.com. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice, 
 *      this list of conditions and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *   3. Neither the name of the copyright holder nor the names of its 
 *      contributors may be used to endorse or promote products derived from
 *      this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package cloud.piranha.http.undertow;

import cloud.piranha.http.impl.DefaultHttpServerProcessor;
import cloud.piranha.http.api.HttpServer;
import cloud.piranha.http.api.HttpServerProcessor;
import io.undertow.Undertow;

/**
 * The Netty HTTP server.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class UndertowHttpServer implements HttpServer {

    /**
     * Stores the HTTP server processor.
     */
    private final HttpServerProcessor httpServerProcessor;

    /**
     * Stores the server port.
     */
    private final int serverPort;

    /**
     * Stores the Undertow server.
     */
    private Undertow undertow;

    /**
     * Constructor.
     */
    public UndertowHttpServer() {
        httpServerProcessor = new DefaultHttpServerProcessor();
        serverPort = 8080;
    }

    /**
     * Constructor.
     *
     * @param serverPort the server port.
     */
    public UndertowHttpServer(int serverPort) {
        this.httpServerProcessor = new DefaultHttpServerProcessor();
        this.serverPort = serverPort;
    }

    /**
     * Constructor.
     *
     * @param serverPort the server port.
     * @param httpServerProcessor the HTTP server processor.
     */
    public UndertowHttpServer(int serverPort, HttpServerProcessor httpServerProcessor) {
        this.httpServerProcessor = httpServerProcessor;
        this.serverPort = serverPort;
    }

    /**
     * Is the server running?
     *
     * @return true if running, false otherwise.
     */
    @Override
    public boolean isRunning() {
        return undertow != null;
    }

    /**
     * Start the server.
     */
    @Override
    public void start() {
        undertow = Undertow.builder()
                .addHttpListener(serverPort, "0.0.0.0")
                .setHandler(new UndertowHttpHandler(httpServerProcessor))
                .build();
        undertow.start();
    }

    /**
     * Stops the server.
     */
    @Override
    public void stop() {
        undertow.stop();
        undertow = null;
    }
}