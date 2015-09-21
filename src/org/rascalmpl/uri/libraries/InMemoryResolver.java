/*******************************************************************************
 * Copyright (c) 2009-2015 CWI
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:

 *   * Paul Klint - Paul.Klint@cwi.nl - CWI
 *   * Davy Landman -davy.landman@gmail.com - CWI
*******************************************************************************/

package org.rascalmpl.uri.libraries;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.NavigableMap;

import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.rascalmpl.uri.FileTree;
import org.rascalmpl.uri.ISourceLocationInputOutput;

/**
 * This resolver is used for example for the scheme "test-modules", which amongst others 
 * generates modules during tests.
 * These modules are implemented via an in-memory "file system" that guarantees
 * that "lastModified" is monotone increasing, i.e. after a write to a file lastModified
 * is ALWAYS larger than for the previous version of the same file.
 * When files are written at high speeed (e.g. with 10-30 ms intervals ), this property is, 
 * unfortunately, not guaranteed on all operating systems.
 * 
 * So if you are writing temporary files very frequently and use lastModified to mark the fields 
 * as dirty, use an instance of this of this resolver to guarantee the dirty marking.
 * 
 * The locations should not use the autority field, as that is ignored.
 *
 */

public abstract class InMemoryResolver implements ISourceLocationInputOutput {
	
    private final String scheme;
    
    private final class InMemoryFileTree extends FileTree { 
        public NavigableMap<String, FSEntry> getFileSystem() {
            return fs;
        }
    }
    
    private InMemoryFileTree fileSystem = new InMemoryFileTree();
    
	public InMemoryResolver(String scheme) {
        this.scheme = scheme;
    }

    @Override
	public String scheme() {
		return scheme;
	}
	
	private static final class File extends FileTree.FSEntry {
		byte[] contents;
		public File() {
		    super(System.currentTimeMillis());
			contents = null;
		}
		public void newContent(byte[] byteArray) {
			long newTimestamp = System.currentTimeMillis();
			if (newTimestamp <= lastModified) {
				newTimestamp =  lastModified + 1;
			}
			lastModified = newTimestamp;
			contents = byteArray;
		}
		public String toString(){
		    return String.valueOf(lastModified) + ":\n" +new String(contents, StandardCharsets.UTF_8);
		}
	}

	private File get(ISourceLocation uri) {
	    return (File) fileSystem.getFileSystem().get(uri.getPath());
	}
	
	@Override
	public InputStream getInputStream(ISourceLocation uri)
			throws IOException {
		File file = get(uri);
		if (file == null) {
			throw new IOException();
		}
		//System.err.println("getInputStream: " + uri + "?" + file.toString());
		return new ByteArrayInputStream(file.contents);
	}

	@Override
	public OutputStream getOutputStream(ISourceLocation uri, boolean append)
			throws IOException {
		return new ByteArrayOutputStream() {
			@Override
			public void close() throws IOException {
				super.close();
				File file = get(uri);
				if (file == null) {
				    file = new File();
				    fileSystem.getFileSystem().put(uri.getPath(), file);
				}
				file.newContent(this.toByteArray());
				//System.err.println("getOutputStream.close " + uri + "?" + file.toString());
			}
		};
	}
	
	@Override
	public long lastModified(ISourceLocation uri) throws IOException {
		File file = get(uri);
		if (file == null) {
			throw new IOException();
		}
		return file.lastModified;
	}
	
	@Override
	public Charset getCharset(ISourceLocation uri) throws IOException {
		return null;
	}

	@Override
	public boolean exists(ISourceLocation uri) {
		return fileSystem.exists(uri.getPath());
	}

	@Override
	public boolean isDirectory(ISourceLocation uri) {
	    return fileSystem.isDirectory(uri.getPath());
	}

	@Override
	public boolean isFile(ISourceLocation uri) {
	    return fileSystem.isFile(uri.getPath());
	}

	@Override
	public String[] list(ISourceLocation uri) throws IOException {
	    return fileSystem.directChildren(uri.getPath());
	}

	@Override
	public boolean supportsHost() {
		return false;
	}

	@Override
	public void mkDirectory(ISourceLocation uri) throws IOException {
	}

	@Override
	public void remove(ISourceLocation uri) throws IOException {
	    fileSystem.getFileSystem().remove(uri.getPath());
	}
}