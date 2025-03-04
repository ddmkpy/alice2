package edu.cmu.cs.stage3.alice.core.event;

/*
 * Copyright (c) 1999-2003, Carnegie Mellon University. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * 
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * 3. Products derived from the software may not be called "Alice",
 *    nor may "Alice" appear in their name, without prior written
 *    permission of Carnegie Mellon University.
 * 
 * 4. All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *    "This product includes software developed by Carnegie Mellon University"
 */

public class SoundEvent extends java.util.EventObject {
	private Object time, dataSource, duration;

	public SoundEvent(
			edu.cmu.cs.stage3.alice.core.responses.SoundResponse source,
			Object value, Object ds, Object dur) {
		super(source);
		time = value;
		dataSource = ds;
		duration = dur;
	}

	public edu.cmu.cs.stage3.alice.core.responses.SoundResponse getSoundResponse() {
		return (edu.cmu.cs.stage3.alice.core.responses.SoundResponse) getSource();
	}

	public Object getTime() {
		return time;
	}

	public Object getDuration() {
		return duration;
	}

	public Object getDataSource() {
		return dataSource;
	}

}
