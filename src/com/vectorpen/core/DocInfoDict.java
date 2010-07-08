/*
 * Copyright (c) 2007-2010, Clemens Akens and Oleg Slobodskoi.
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
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

package com.vectorpen.core;

import java.util.ArrayList;

public final class DocInfoDict
{
	private String title;
	private String author;
	private String subject;
	private ArrayList<String> keywords;

	public DocInfoDict(String title, String author, String subject, ArrayList<String> keywords)
	{
		this.setTitle(title);
		this.setAuthor(author);
		this.setSubject(subject);
		this.setKeywords(keywords);
	}

	protected String getTitle()
	{
		return this.title;
	}

	protected String getAuthor()
	{
		return this.author;
	}

	protected String getSubject()
	{
		return this.subject;
	}

	protected ArrayList<String> getKeywords()
	{
		return this.keywords;
	}

	protected void setTitle(String title)
	{
		if (title != null)
		{
			this.title = title;
		}
		else
		{
			this.title = "";
		}
	}

	protected void setAuthor(String author)
	{
		if (author != null)
		{
			this.author = author;
		}
		else
		{
			this.author = "";
		}
	}

	protected void setSubject(String subject)
	{
		if (subject != null)
		{
			this.subject = subject;
		}
		else
		{
			this.subject = "";
		}
	}

	protected void setKeywords(ArrayList<String> keywords)
	{
		if (keywords != null)
		{
			for (int index = keywords.size() - 1; index >= 0; index--)
			{
				if (keywords.get(index) != null)
				{
					if (keywords.get(index).compareToIgnoreCase("") == 0)
						keywords.remove(index);
				}
				else
				{
					keywords.remove(index);
				}
			}

			this.keywords = keywords;
		}
		else
		{
			this.keywords = new ArrayList<String>();
		}
	}
}