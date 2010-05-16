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