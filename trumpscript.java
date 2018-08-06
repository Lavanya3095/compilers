package compiler;

import java.io.BufferedReader;
import java.util.*;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class trumpscript
{
	
	static String file="",say1="";static char nfch = 0;
	static int flag;static char special=0;
	static int[] int_tok=new int[115];
	static String[] str_tok=new String[115];
	static int ind=0;
	static int[] stack=new int[50];
	static Map<String,String> SYMTAB = new HashMap<String,String>();
	static class BOOKKEEPER{
	public static void BOOKKEEPER1(String type,String token)
	{
		SYMTAB.put(type.toLowerCase(), token);
	}
	}
	static class SCANNER{
	public static void SCANNER1(char ch) 
	{
		if(ch=='#'|| flag==1)
		{
			if(ch!='\n')
			{flag=1;
			return;}
			else 
			{flag=0;
			return;}
		}
		else
		{
		if(ch==' '||ch=='$'||ch=='\b'||ch=='\t'||ch=='\r'||ch=='\n')
		{
			if(file!=""&&file!=" ")
			{
					nfch=0;
					if(file.contains(",")==true)
					{
						nfch=',';
						String[] sp=file.split(",");
						for(String temp:sp)
							file=temp;
					}
					else if(file.contains(":")==true)
					{
						nfch=':';
						String[] sp=file.split(":");
						for(String temp:sp)
							file=temp;
					}
					else if(file.contains(";")==true)
					{
						nfch=';';
						String[] sp=file.split(";");
						for(String temp:sp)
							file=temp;
					}
					else if(file.contains("?")==true)
					{
						nfch='?';
						String[] sp=file.split("\\?");
						for(String temp:sp)
							file=temp;
					}
					else if(file.contains("!")==true)
					{
						nfch='!';
						String[] sp=file.split("!");
						for(String temp:sp)
							file=temp;
					}
					else if(file.contains("(")==true)
					{
						nfch='(';
						String[] sp=file.split("\\(");
						for(String temp:sp)
							file=temp;
					}
					else if(file.contains(")")==true)
					{
						nfch=')';
						String[] sp=file.split("\\)");
						for(String temp:sp)
							file=temp;
					}
					else if(file.contains("\""))
					{
						String check_double=Pattern.quote("\"");
						String[] check_double1=file.split(check_double,2);
						for(String temp:check_double1)
						{
							if(temp.contains("\""))
							{
								String[] say=temp.split("\"");
								for(String temp1:say)
									say1=temp1;
							}
							else
								file=temp;
						}
					}	
				}
				char [] iptoken= file.toCharArray();
				int iptokenl= iptoken.length;
				int i=0;
				if(iptokenl!=0)
				{
				if(iptoken[0]!=','&&iptoken[0]!=';'&&iptoken[0]!=':'
						&&iptoken[0]!='('&&iptoken[0]!=')'&&iptoken[0]!='?'
							&&iptoken[0]!='!'&&iptokenl==1)
					check_special(i,iptoken);
				else
				{
						switch(iptoken[i])
					{
					case 'm': case 'M': 
						switch(iptoken[i+1])
						{
						case 'a': case'A': 
							switch(iptoken[i+2])
							{
							case 'k':case'K':
								if((iptoken[i+3]=='e'||iptoken[i+3]=='E')
										&&(iptokenl==4)) 
								{	//BOOKKEEPER(file,"KEYWORD");
									//System.out.println(file+"\tKeyword"+int_tok[0]);
									int_tok[ind]=4;str_tok[ind]=file;
									ind++;
									}
								else
									check_special(i+3,iptoken);
							break;
							default: check_special(i+2,iptoken);
									 break;
							}
						break;
						case 'o':case'O': 
							switch(iptoken[i+2])
							{
							case 'r':case'R':
								if((iptoken[i+3]=='e'||iptoken[i+3]=='E')
										&& (iptokenl==4)) 
									{
									//BOOKKEEPER(file,"KEYWORD");
									int_tok[ind]=24;str_tok[ind]=file;
									ind++;
									//System.out.println(file+"\tKeyword");
									}
								else
									check_special(i+3,iptoken);
							break;
							default: check_special(i+2,iptoken);
									 break;
							}
						break;
						default: check_special(i+1,iptoken);
								 break;
						}
					break; 
					case 'p':case'P': 
						switch(iptoken[i+1])
						{
						case 'r':case'R': 
							switch(iptoken[i+2])
							{
							case 'o':case'O': 
								switch(iptoken[i+3])
								{
								case 'g':case'G':
									switch(iptoken[i+4])
									{
									case 'r':case'R':
										switch(iptoken[i+5])
										{
										case 'a':case'A':
											switch(iptoken[i+6])
											{
											case 'm':case'M': 
												switch(iptoken[i+7])
												{
													case 'm':case'M':
														switch(iptoken[i+8])
														{
														case 'i':case'I':
															switch(iptoken[i+9])
															{
															case 'n':case'N':
																if((iptoken[i+10]=='g'||
																	iptoken[i+10]=='G')
																		&&iptokenl==11)
																	{
																	//BOOKKEEPER(file,"KEYWORD");
																	int_tok[ind]=5;str_tok[ind]=file;
																	ind++;
																	//System.out.println(file+"\tKeyword");
																	}
																else
																	check_special(i+10,iptoken);
															break;
															default: check_special(i+9,iptoken);
																	 break;
															}
														break;
														default: check_special(i+8,iptoken);
																 break;
														}
												break;
												default: check_special(i+7,iptoken);
														 break;
												}
											break;
											default: check_special(i+6,iptoken);
													 break;
											}
										break;
										default: check_special(i+5,iptoken);
												 break;
										}
									break;
									default: check_special(i+4,iptoken);
											 break;
									}
								break;
								default: check_special(i+3,iptoken);
										 break;
								}
							break;
							default: check_special(i+2,iptoken);
									 break;
							}
						break;
						case 'l':case'L':
							switch(iptoken[i+2])
							{
							case 'u':case'U':
								if((iptoken[i+3]=='s'||iptoken[i+3]=='S')
										&&iptokenl==4)
									{
									//BOOKKEEPER(file,"KEYWORD");
									int_tok[ind]=25;str_tok[ind]=file;
									ind++;
									//int_tok[ind]=32;str_tok[ind]="(";
									//ind++;
									//System.out.println(file+"\tKeyword");
									}
								else
									check_special(i+3,iptoken);
								break;
							default: check_special(i+2,iptoken);
									 break;
							}
						break;
						default: check_special(i+1,iptoken);
								 break;
						}
					break;
					case 'g':case'G':
						switch(iptoken[i+1])
						{
						case 'r':case'R':
							switch(iptoken[i+2])
							{
							case 'e':case'E':
								switch(iptoken[i+3])
								{
								case 'a':case'A':
									if((iptoken[i+4]=='t'||iptoken[i+4]=='T')
											&&iptokenl==5)
										{
										//BOOKKEEPER(file,"KEYWORD");
										int_tok[ind]=6;str_tok[ind]=file;
										ind++;
										//System.out.println(file+"\tKeyword");
										}
									else
										check_special(i+4,iptoken);
								break;
								default: check_special(i+3,iptoken); 
								}
							break;
							default: check_special(i+2,iptoken);
									 break;
							}
						break;
						default: check_special(i+1,iptoken);
								 break;
						}
						break;
					case 'a':case'A':
						switch(iptoken[i+1]) 
						{
						case 'g':case'G':
							switch(iptoken[i+2])
							{
							case 'a':case'A':
								switch(iptoken[i+3])
								{
								case 'i':case'I':
									if((iptoken[i+4]=='n'||iptoken[i+4]=='N')
											&&iptokenl==5)
										{
										//BOOKKEEPER(file,"KEYWORD");
										int_tok[ind]=7;str_tok[ind]=file;
										ind++;
										//System.out.println(file+"\tKeyword");
										}
									else
										check_special(i+4,iptoken);
								break;
								default: check_special(i+3,iptoken);
										 break;
								}
							break;
							default: check_special(i+2,iptoken);
									 break;
							}
						break;
						case 'm':case'M':
							switch(iptoken[i+2])
							{
							case'e':case'E':
								switch(iptoken[i+3])
								{
								case 'r':case'R':
									switch(iptoken[i+4])
									{
									case 'i':case'I':
										switch(iptoken[i+5])
										{
										case'c':case'C': 
											if((iptoken[i+6]=='a'||iptoken[i+6]=='a')
													&&iptokenl==7)
												{
												//BOOKKEEPER(file,"KEYWORD");
												int_tok[ind]=8;str_tok[ind]=file;
												ind++;
												//System.out.println(file+"\tKeyword");
												}
											else
												check_special(i+6,iptoken);
										break;
										default: check_special(i+5,iptoken);
												 break;
										}
									break;
									default: check_special(i+4,iptoken);
											 break;
									}
								break;
								default: check_special(i+3,iptoken);
										 break;
								}
							break;
							default: check_special(i+2,iptoken);
									 break;
								}
						break;
						case'n':case'N':
							if((iptoken[i+2]=='d'||iptoken[i+2]=='D')
									&&iptokenl==3)
								{
								//BOOKKEEPER(file,"KEYWORD");
								int_tok[ind]=21;str_tok[ind]=file;
								ind++;
								//System.out.println(file+"\tKeyword");
								}
							else
								check_special(i+2,iptoken);
						break;
						case 's':case'S':
							if(iptokenl==2)
								{
								//BOOKKEEPER(file,"KEYWORD");
								int_tok[ind]=14;str_tok[ind]=file;
								ind++;
								//System.out.println(file+"\tKeyword");
								}
							else
								check_special(i+2,iptoken);
						break;
						default: check_special(i+1,iptoken);
								 break;
						}
					break;
					case'i':case'I': 
						if((iptoken[i+1]=='s'||iptoken[i+1]=='S')
								&&iptokenl==2)
							{
							//BOOKKEEPER(file,"KEYWORD");
							int_tok[ind]=9;str_tok[ind]=file;
							ind++;
							//System.out.println(file+"\tKeyword");
							}
						else if((iptoken[i+1]=='f'||iptoken[i+1]=='F')
								&&iptokenl==2)
							{
							//BOOKKEEPER(file,"KEYWORD");
							int_tok[ind]=13;str_tok[ind]=file;
							ind++;
							//System.out.println(file+"\tKeyword");
							}
						else	
							check_special(i+1,iptoken);
					break;
					case'e':case'E':
						switch(iptoken[i+1])
						{
						case'l':case'L':
							switch(iptoken[i+2])
							{
							case 's':case'S':
								if((iptoken[i+3]=='e'||iptoken[i+3]=='E')
										&&iptokenl==4)
									{
									//BOOKKEEPER(file,"KEYWORD");
									int_tok[ind]=10;str_tok[ind]=file;
									ind++;
									//System.out.println(file+"\tKeyword");
									}
								else
									check_special(i+3,iptoken);
							break;
							default: check_special(i+2,iptoken);
									 break;
							}
						break;
						default: check_special(i+1,iptoken);
								 break;
						}
					break;
					case 'n':case'N':
						switch(iptoken[i+1])
						{
						case'u':case'U':
							switch(iptoken[i+2]) {
							case'm':case'M':
								switch(iptoken[i+3])
								{
								case'b':case'B':
									switch(iptoken[i+4]) 
									{
									case 'e':case'E':
										if((iptoken[i+5]=='r'||iptoken[i+5]=='R')
												&&iptokenl==6)
											{
											//BOOKKEEPER(file,"KEYWORD");
											int_tok[ind]=11;str_tok[ind]=file;
											ind++;
											//System.out.println(file+"\tKeyword");
											}
										else
											check_special(i+5,iptoken);
									break;
									default: check_special(i+4,iptoken);
											 break;
									}
								break;
								default: check_special(i+3,iptoken);
								 		 break;
								}
							break;
							default: check_special(i+2,iptoken);
							 		 break;
							}
						break;
						case'o':case'O':
							if((iptoken[i+2]=='t'||iptoken[i+2]=='R')
									&&iptokenl==3)
								{
								//BOOKKEEPER(file,"KEYWORD");
								int_tok[ind]=20;str_tok[ind]=file;
								ind++;
								//System.out.println(file+"\tKeyword");
								}
							else
								check_special(i+2,iptoken);
						break;
						default: check_special(i+1,iptoken);
						 		 break;
						}
					break;
					case 'b':case'B':
						switch(iptoken[i+1])
						{
						case'o':case'O':
							switch(iptoken[i+2])
							{
							case 'o':case'O':
								switch(iptoken[i+3])
								{
								case'l':case'L':
									switch(iptoken[i+4])
									{
									case'e':case'E':
										switch(iptoken[i+5])
										{
										case 'a':case'A':
											if((iptoken[i+6]=='n'||
												iptoken[i+6]=='N')&&iptokenl==7)
												{ int_tok[ind]=12;str_tok[ind]=file;
												    ind++;
												//BOOKKEEPER(file,"KEYWORD");
												//System.out.println(file+"\tKeyword");
												}
											else
												check_special(i+6,iptoken);
										break;
										default: check_special(i+5,iptoken);
										 		 break;
										}
									break;
									default: check_special(i+4,iptoken);
							 		 		 break;
									}
								break;
								default: check_special(i+3,iptoken);
						 		 		 break;
								}
							break;
							default: check_special(i+2,iptoken);
					 		 		 break;
							}
						break;
						default: check_special(i+1,iptoken);
				 		 		 break;
						}
					break;
					case 'l':case'L':
						switch(iptoken[i+1])
						{
						case'e':case'E':
							switch(iptoken[i+2])
							{
							case 's':case'S':
								if((iptoken[i+3]=='s'||iptoken[i+3]=='S')
										&&iptokenl==4)
									{
									//BOOKKEEPER(file,"KEYWORD");
									int_tok[ind]=23;str_tok[ind]=file;
									ind++;
									//System.out.println(file+"\tKeyword");
									}
								else
									check_special(i+3,iptoken);
							break;
							default: check_special(i+2,iptoken);
							 		 break;
							}
						break;
						case'o':case'O':
							switch(iptoken[i+2])
							{
							case 'n':case'N':
								if((iptoken[i+3]=='g'||iptoken[i+3]=='G')
										&&iptokenl==4)
									{
									//BOOKKEEPER(file,"KEYWORD");
									int_tok[ind]=15;str_tok[ind]=file;
									ind++;
									//System.out.println(file+"\tKeyword");
									}
								else
									check_special(i+3,iptoken);
							break;
							default: check_special(i+2,iptoken);
							 		 break;
							}
						break;
						case 'i':case'I':
							if((iptoken[i+2]=='e'||iptoken[i+2]=='E')
									&&iptokenl==3)
								{
								//BOOKKEEPER(file,"KEYWORD");
								int_tok[ind]=19;str_tok[ind]=file;
								ind++;
								//System.out.println(file+"\tKeyword");
								}
							else
								check_special(i+2,iptoken);
						break;
						default: check_special(i+1,iptoken);
						 		 break;
						}
					break;
					case 't':case'T':
						switch(iptoken[i+1])
						{
						case'e':case'E':
							switch(iptoken[i+2])
							{
							case 'l':case'L':
								if((iptoken[i+3]=='l'||iptoken[i+3]=='L')
										&&iptokenl==4)
									{
									//BOOKKEEPER(file,"KEYWORD");
									int_tok[ind]=16;str_tok[ind]=file;
									ind++;
									//System.out.println(file+"\tKeyword");
									}
								else
									check_special(i+3,iptoken);
							break;
							default: check_special(i+2,iptoken);
							 		 break;
							}
						break;
						case'i':case'I':
							switch(iptoken[i+2])
							{
							case'm':case'M':
								switch(iptoken[i+3])
								{
								case'e':case'E':
									if((iptoken[i+4]=='s'||iptoken[i+4]=='S')
											&&iptokenl==5)
										{
										//BOOKKEEPER(file,"KEYWORD");
										int_tok[ind]=26;str_tok[ind]=file;
										ind++;
										//System.out.println(file+"\tKeyword");
										}
									else
										check_special(i+4,iptoken);
								break;
								default: check_special(i+3,iptoken);
								 		 break;
								}
							break;
							default: check_special(i+2,iptoken);
					 		 		 break;
							}
						break;
						default: check_special(i+1,iptoken);
				 		 		 break;
						}
					break;
					case's':case'S':
						switch(iptoken[i+1])
						{
						case 'a':case'A':
							if((iptoken[i+2]=='y'||iptoken[i+2]=='Y')
									&&iptokenl==3)
								{
								//BOOKKEEPER(file,"KEYWORD");
								int_tok[ind]=17;str_tok[ind]=file;
								ind++;
								//System.out.println(file+"\tKeyword");
								}
							else
								check_special(i+2,iptoken);
						break;
						default: check_special(i+1,iptoken);
						 		 break;
						}
					break;
					case'f':case'F':
						switch(iptoken[i+1])
						{
						case 'a':case'A':
							switch(iptoken[i+2])
							{
							case'c':case'C':
								if((iptoken[i+3]=='t'||iptoken[i+3]=='T')
										&&iptokenl==4)
									{
									//BOOKKEEPER(file,"KEYWORD");
									int_tok[ind]=18;str_tok[ind]=file;
									ind++;
									//System.out.println(file+"\tKeyword");
									}
								else
									check_special(i+3,iptoken);
							break;
							default: check_special(i+2,iptoken);
							 		 break;
							}
						break;
						default: check_special(i+1,iptoken);
				 		 		 break;
						}
					break;
					case'o':case'O':
						if((iptoken[i+1]=='r'||iptoken[i+1]=='R')
								&&iptokenl==2)
							{
							//BOOKKEEPER(file,"KEYWORD");
							int_tok[ind]=22;str_tok[ind]=file;
							ind++;
							//System.out.println(file+"\tKeyword");
							}
						else
							check_special(i+1,iptoken);
					break;
					case '1':case '2':case '3':case '4':case '5':
						case '6':case '7':case '8':case '9':
						int d=0;
						for(int n=0;n<iptokenl;n++)
						{
							if(!Character.isDigit(iptoken[n]))
									d=1;
						}
						if(d==1)
							ERRORHANDLER.ERRORHANDLER1(file,2);
						if(d==0)
						{
							int num=Integer.parseInt(file);
							if(num>1000000)
								{
								BOOKKEEPER.BOOKKEEPER1(file,"CONSTANT");
								int_tok[ind]=2;str_tok[ind]=file;
								ind++;
								//System.out.println(file+"\tConstant ");
								}
							else
								ERRORHANDLER.ERRORHANDLER1(file,2);
						}
						break;
					default:
							int c=0,f=0;
							if(iptoken[0]==','||iptoken[0]==';'||
									iptoken[0]==':'||iptoken[0]=='('||
									iptoken[0]==')'||iptoken[0]=='?'||
									iptoken[0]=='!'||iptokenl==1)
							{
								f=1;
								break;
							}
						if(f==0)	
							{
									for(int n=0;n<iptokenl;n++)
								{
									
									if((!Character.isDigit(iptoken[n]))&&
											(!Character.isAlphabetic(iptoken[n])))
										c=1;
								}
								if(c==1)
									ERRORHANDLER.ERRORHANDLER1(file,1);
								else
									{
									BOOKKEEPER.BOOKKEEPER1(file,"IDENTIFIER");
									int_tok[ind]=1;str_tok[ind]=file;
									ind++;
									//System.out.println(file+"\tIdentifier");
									}
							}
						break;
				}
				switch(nfch)
				{
				case',': int_tok[ind]=27;str_tok[ind]=Character.toString(nfch);
						 ind++;
						 //System.out.println(nfch+"\tSpecial symbol");
						 break;
				case':': int_tok[ind]=29;str_tok[ind]=Character.toString(nfch);
						 ind++;
						 //System.out.println(nfch+"\tSpecial symbol");
				 		 break;
				case';': int_tok[ind]=28;str_tok[ind]=Character.toString(nfch);
						 ind++;
						 //System.out.println(nfch+"\tSpecial symbol");
				 		 break;
				case'?': int_tok[ind]=31;str_tok[ind]=Character.toString(nfch);
						 ind++;
						 //System.out.println(nfch+"\tSpecial symbol");
				 		 break;
				case'(': int_tok[ind]=32;str_tok[ind]=Character.toString(nfch);
						 ind++;
						 //System.out.println(nfch+"\tSpecial symbol");
				 		 break;
				case')': int_tok[ind]=33;str_tok[ind]=Character.toString(nfch);
						 ind++;
						 //System.out.println(nfch+"\tSpecial symbol");
				 		 break;
				case'!': int_tok[ind]=30;str_tok[ind]=Character.toString(nfch);
						 ind++;
						 //System.out.println(nfch+"\tSpecial symbol");
				 		 break;
				default: break;
				}
				if(say1!="")
				{
					int_tok[ind]=3;str_tok[ind]=say1;
					ind++;
					//System.out.println(say1+"\tString");
					BOOKKEEPER.BOOKKEEPER1(say1,"STRING");
					say1="";
				}
			}
			file="";
		}
		}
		else
			file=file+Character.toString(ch);
		}
	}
	public static void check_special(int index, char[] word)
	{
		int flag1=0;
		for(int j=index;j<word.length;j++)
		{
			if(word[j]==','||word[j]=='?'||word[j]==':'||
					word[j]==';'||word[j]=='('||word[j]==')')
			flag1=1;
		}
		if(flag1==1)
			ERRORHANDLER.ERRORHANDLER1(String.copyValueOf(word),2);
		else
		{
			int_tok[ind]=1;str_tok[ind]=file;
			ind++;
			//System.out.println(file+"identifier");
			BOOKKEEPER.BOOKKEEPER1(file,"IDENTIFIER");
		}
	}
	}
	static class ERRORHANDLER{
	public static void ERRORHANDLER1(String err,int type)
	{
		if(type==1)
			System.out.println("["+err+"]"+"error: This is a country where we speak English");
		else if(type==2)
			System.out.println("["+err+"]"+"error: I'm really rich, part of the beauty of me is I'm very rich");
		else
			System.out.println("Trump does not want to hear");
	}
	}
	static class PARSER{
		static int[] ruleno= {34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53};
		static String[] rules= {"<Trump>","<first>","<last>","<stmts>","<more-stmts>","<stmt>","<decl>","<type>","<asmt>","<cond>","<loop>","<output>","<ids>","<more-ids>","<expr>","<bool>","<bool-taid>","<test>","<arith>","<arith-tail>"};
		static int loopno=0,top=-1,look;
		static int steps=0;
		public static void PARSER1() throws IOException
		{
			int read;
			BufferedReader br2=new BufferedReader(new FileReader("D:\\spring 18\\CC\\program.txt"));
			//System.out.println("TOKEN\t\tTYPE");
			while((read=br2.read()) !=-1)
			{	
				SCANNER.SCANNER1((char)read);
			}
			SCANNER.SCANNER1('$');
			br2.close();
			int_tok[54]=3;str_tok[54]="continue";int_tok[55]=28;str_tok[55]=";";
			int_tok[75]=3;str_tok[75]="stop";int_tok[76]=28;str_tok[76]=";";
			int_tok[107]=3;str_tok[107]="done";int_tok[108]=28;str_tok[108]=";";
			//for(int i=0;i<int_tok.length;i++)
			//System.out.println(int_tok[i]+"\t"+str_tok[i]);
			stack[++top]=0;stack[++top]=34;
			System.out.println("steps\tstack top\tLookahead\tAction");
			System.out.println(steps+"\tZ0{0}\t\t\t\tPUSH<Trump>");
			steps++;
			while(loopno!=97)
			{
				int match_flag=0;
				look=int_tok[loopno];
				while(match_flag==0)
				{
				if(look==stack[top])
				{
					if(int_tok[loopno]!=1 && int_tok[loopno]!=2)
					{	
					System.out.println(steps+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\t"+str_tok[loopno]+int_tok[loopno]+"\tpop and consume input token");
					}
					else if(int_tok[loopno]==1)
						System.out.println(steps+"\t"+"[id]"+int_tok[loopno]+"\t\t"+"[id]"+int_tok[loopno]+"\tpop and consume input token");
					else
						System.out.println(steps+"\t"+"[const]"+int_tok[loopno]+"\t\t"+"[const]"+int_tok[loopno]+"\tpop and consume input token");
					steps++;
					pop();
					match_flag=1;
				}
				else
				{
					switch(stack[top])
					{
					case 34:System.out.println(steps+"\t"+rules[0] +ruleno[0]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 1");
							steps++;
							pop();
							push(36);
							push(37);
							push(35);
							break;
					case 35:System.out.println(steps+"\t"+rules[1] +ruleno[1]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 2");
							steps++;		
							pop();
							push(7);
							push(6);
							push(5);
							push(4);
							break;
					case 36:break;
					case 37:System.out.println(steps+"\t"+rules[3] +ruleno[3]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 4");
							steps++;
							pop();
							push(38);
							push(28);
							push(39);
							break;
					case 38:if(int_tok[loopno]==1)
								System.out.println(steps+"\t"+rules[4]+ruleno[4]+"\t"+"[id]"+int_tok[loopno]+"\t\tuse rule 5");
							else
								System.out.println(steps+"\t"+rules[4]+ruleno[4]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 5");
							steps++;
							pop();
							push(38);
							push(28);
							push(39);
							break;
					case 39:if(int_tok[loopno]==1)
							{
							System.out.println(steps+"\t"+rules[5] +ruleno[5]+"\t"+"[id]"+int_tok[loopno]+"\t\tuse rule 8");
							steps++;
							pop();
							push(42);
							}
							if (int_tok[loopno]!=1 && int_tok[loopno]!=14 && int_tok[loopno]!=16)
							{
								System.out.println(steps+"\t"+rules[5] +ruleno[5]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 7");
								steps++;
								pop();
								push(40);
							}
							if(int_tok[loopno]==16)
							{
							System.out.println(steps+"\t"+rules[5] +ruleno[5]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 11");
							steps++;
							pop();
							push(45);}
					if(int_tok[loopno]==14)
					{
					System.out.println(steps+"\t"+rules[5] +ruleno[5]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 10");
					steps++;
					pop();
					push(44);}
					break;
					case 40:System.out.println(steps+"\t"+rules[6] +ruleno[6]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 12");
							steps++;
							pop();
							push(41);
							push(46);
							push(4);
							break;
					case 41:if(int_tok[loopno]==11)
							{
							System.out.println(steps+"\t"+rules[7]+ruleno[7]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 13");
							steps++;
							pop();
							push(11);
							}
							else
							{
								System.out.println(steps+"\t"+rules[7]+ruleno[7]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 14");
								steps++;
								pop();
								push(12);	
							}
							break;
					case 42:System.out.println(steps+"\t"+rules[8]+ruleno[8]+"\t"+"[id]"+int_tok[loopno]+"\t\tuse rule 15");
							steps++;
							pop();
							push(48);
							push(9);
							push(1);
							break;
					case 44:System.out.println(steps+"\t"+rules[10]+ruleno[10]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 17");
					steps++;
					pop();
					push(30);
					push(37);
					push(29);
					push(28);
					push(49);
					push(27);
					push(14);
					push(15);
					push(14);
					break;
					case 45:if(int_tok[loopno]==16)
					{
						System.out.println(steps+"\t"+rules[11]+ruleno[11]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 18");
						steps++;
						pop();
						push(20);
						push(16);
					}break;
					case 46:System.out.println(steps+"\t"+rules[12]+ruleno[12]+"\t"+"[id]"+int_tok[loopno]+"\t\tuse rule 20");
							steps++;
							pop();
							push(47);
							push(1);
							break;
					case 47:if(int_tok[loopno]!=1)
							{
								System.out.println(steps+"\t"+rules[13]+ruleno[13]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 22");
								steps++;
								pop();
							}
							else
							{
								System.out.println(steps+"\t"+rules[13]+ruleno[13]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 21");
								steps++;
								pop();
								push(47);
								push(1);
							}
							break;
					case 48:if(int_tok[loopno]!=2)
					{
						System.out.println(steps+"\t"+rules[14]+ruleno[14]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 23");
						steps++;
						pop();
						push(49);
					}
					else
					{
						System.out.println(steps+"\t"+rules[14]+ruleno[14]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 24");
						steps++;
						pop();
						push(52);
					}
					break;
					case 49:if(int_tok[loopno]==18)
					{
						System.out.println(steps+"\t"+rules[15]+ruleno[15]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 25");
						steps++;
						pop();
						push(50);
						push(18);
						
					}
					if(int_tok[loopno]==19)
					{
						System.out.println(steps+"\t"+rules[15]+ruleno[15]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 26");
						steps++;
						pop();
						push(50);
						push(19);
						
					}
					break;
					case 50:if(int_tok[loopno]!=21 && int_tok[loopno]!=22)
					{
						System.out.println(steps+"\t"+rules[16]+ruleno[16]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 31");
						steps++;
						pop();}
					if(int_tok[loopno]==22)
					{
						System.out.println(steps+"\t"+rules[16]+ruleno[16]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 30");
						steps++;
						pop();
						push(49);
						push(22);
						}
					break;
					case 52:if(int_tok[loopno]==2)
					{
						System.out.println(steps+"\t"+rules[18]+ruleno[18]+"\t"+"[const]"+int_tok[loopno]+"\t\tuse rule 36");
						steps++;
						pop();
						push(53);
						push(2);
					}break;
					case 53:if(int_tok[loopno]!=25 && int_tok[loopno]!=26)
					{
						System.out.println(steps+"\t"+rules[19]+ruleno[19]+"\t"+str_tok[loopno]+int_tok[loopno]+"\t\tuse rule 36");
						steps++;
						pop();
						
					}break;
					}
				}
				}
				loopno++;
			}
		}
		public static void push(int 
				rno) 
		{
			stack[++top]=rno;
		}
		public static void pop()
		{	
			top--;
		}
	}
	public static void main(String args[]) throws IOException 
	{
		// Displaying the input file...
		System.out.println("INPUT PROGRAM");
		BufferedReader br1=new BufferedReader(new FileReader("D:\\spring 18\\CC\\program.txt"));
		String program=null;
		while((program = br1.readLine()) != null)
			System.out.println(program);
		br1.close();
		PARSER.PARSER1();
		System.out.println("----------------------------------------");
		System.out.println("\tSYMBOL TABLE");
		System.out.println("----------------------------------------");
		System.out.println("|TOKEN\t\t|ATTRIBUTE");
		System.out.println("----------------------------------------");
		for(String key: SYMTAB.keySet())
			System.out.println("|"+key + "\t\t|" + SYMTAB.get(key)+"\t|");
		System.out.println("----------------------------------------");
	}
}
