package com.qbq.packagescan;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;

public class PackageScanUtils {
	
	  public Set<Class<?>> getClassesInPackage(String pack, boolean scanjar)
	  {
	    Set classesSet = new LinkedHashSet();
	    if ((pack == null) || ("".equals(pack.trim()))) {
	      lwlException("the package [ " + pack + " ] not exists");
	      return classesSet;
	    }

	    boolean recursive = true;

	    String packageName = pack;
	    String packageDirName = packageName.replace('.', '/');
	    try
	    {
	      Enumeration dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);

	      if (!dirs.hasMoreElements()) {
	        lwlException("the package [ " + pack + " ] not exists");
	        return classesSet;
	      }

	      while (dirs.hasMoreElements())
	      {
	        URL url = (URL)dirs.nextElement();

	        String protocol = url.getProtocol();

	        if ("file".equals(protocol)) {
	          Logger.getLogger("lwl").info("file(class) type to be scanned");

	          String filePath = URLDecoder.decode(url.getFile(), "UTF-8");

	          findAndAddClassesInPackageByFile(packageName, filePath, recursive, classesSet);
	        } else if (("jar".equals(protocol)) && (scanjar))
	        {
	          Logger.getLogger("lwl").info("jar type to be scanned");

	          JarFile jar = ((JarURLConnection)url.openConnection()).getJarFile();

	          Enumeration entries = jar.entries();

	          while (entries.hasMoreElements())
	          {
	            JarEntry entry = (JarEntry)entries.nextElement();
	            String name = entry.getName();

	            if (name.charAt(0) == '/') {
	              name = name.substring(1);
	            }

	            if (name.startsWith(packageDirName)) {
	              int idx = name.lastIndexOf('/');
	              if (idx != -1) {
	                packageName = name.substring(0, idx).replace('/', '.');
	              }

	              if ((idx != -1) || (recursive))
	              {
	                if ((name.endsWith(".class")) && (!entry.isDirectory()))
	                {
	                  String className = name.substring(packageName.length() + 1, name.length() - 6);

	                  classesSet.add(Thread.currentThread().getContextClassLoader()
	                    .loadClass(packageName + '.' + className));
	                }
	              }
	            }
	          }
	        }
	      }
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	      e.printStackTrace();
	    }

	    return classesSet;
	  }

	  private void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes)
	  {
	    File dir = new File(packagePath);

	    if ((!dir.exists()) || (!dir.isDirectory())) {
	      Logger.getLogger("lwl").info("异常信息 Exception " + packageName + " 可能不存在");
	      return;
	    }

	    File[] dirfiles = dir.listFiles(new FileFilter()
	    {
	      public boolean accept(File file)
	      {
	        return ((recursive) && (file.isDirectory())) || 
	          (file.getName().endsWith(".class"));
	      }
	    });
	    try
	    {
	      for (File file : dirfiles)
	      {
	        if (file.isDirectory()) {
	          findAndAddClassesInPackageByFile(
	            packageName + "." + file.getName(), 
	            file.getAbsolutePath(), recursive, classes);
	        }
	        else {
	          String className = file.getName().substring(0, file.getName().length() - 6);

	          classes.add(Thread.currentThread().getContextClassLoader()
	            .loadClass(packageName + '.' + className));
	        }
	      }

	    }
	    catch (ClassNotFoundException e)
	    {
	      e.printStackTrace();
	    }
	  }

	  private void lwlException(String dec)
	  {
	    try {
	      throw new Exception(dec);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

}
