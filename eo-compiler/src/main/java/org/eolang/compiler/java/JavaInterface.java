/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 eolang.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.eolang.compiler.java;

import com.google.common.base.Joiner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;
import org.eolang.compiler.syntax.Method;

/**
 * File with java interface.
 *
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class JavaInterface implements JavaFile {
    /**
     * Interface name.
     */
    private final String name;

    /**
     * Methods.
     */
    private final Collection<Method> methods;

    /**
     * Ctor.
     *
     * @param name Interface name
     * @param methods Interface methods
     */
    public JavaInterface(final String name, final Collection<Method> methods) {
        this.name = name;
        this.methods = methods;
    }

    @Override
    public Path path() {
        return Paths.get(String.format("%s.java", this.name));
    }

    @Override
    public String code() {
        return String.format(
            "package eo;\n\npublic interface %s {\n    %s\n}",
            this.name,
            Joiner.on("\n    ").join(
                this.methods.stream().map(
                    m -> String.format("%s;", m.java())
                ).collect(Collectors.toList())
            )
        );
    }
}
