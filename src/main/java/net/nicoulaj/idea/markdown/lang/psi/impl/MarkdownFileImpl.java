/*
 * Copyright (c) 2011 Julien Nicoulaud <julien.nicoulaud@gmail.com>
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package net.nicoulaj.idea.markdown.lang.psi.impl;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import net.nicoulaj.idea.markdown.file.MarkdownFileType;
import net.nicoulaj.idea.markdown.lang.psi.MarkdownFile;
import net.nicoulaj.idea.markdown.lang.psi.api.MarkdownPsiElement;
import net.nicoulaj.idea.markdown.lang.psi.visitors.MarkdownElementVisitor;
import org.jetbrains.annotations.NotNull;

/**
 * TODO Add Asdoc comment.
 *
 * @author Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * @since 0.1
 */
public class MarkdownFileImpl extends PsiFileBase implements MarkdownFile {

    public MarkdownFileImpl(FileViewProvider viewProvider) {
        super(viewProvider, MarkdownFileType.LANGUAGE);
    }

    public IElementType getTokenType() {
        return null;
    }

    public void accept(MarkdownElementVisitor visitor) {
        visitor.visitElement(this);
    }

    public void acceptChildren(MarkdownElementVisitor visitor) {
        PsiElement child = getFirstChild();
        while (child != null) {
            if (child instanceof MarkdownPsiElement) {
                ((MarkdownPsiElement) child).accept(visitor);
            }
            child = child.getNextSibling();
        }
    }

    @NotNull
    public FileType getFileType() {
        return MarkdownFileType.MARKDOWN_FILE_TYPE;
    }
}
