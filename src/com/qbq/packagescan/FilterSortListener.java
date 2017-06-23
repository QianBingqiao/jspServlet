package com.qbq.packagescan;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.qbq.anno.WebFilterSort;
@WebListener
public class FilterSortListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce)
	  {
	    ServletContext context = sce.getServletContext();
	    String filterInitDebug = context.getInitParameter("filterInitDebug");
	    String filterInitScanJar = context.getInitParameter("filterInitScanJar");
	    String filterPackage = context.getInitParameter("filterPackage");

	    boolean debug = false;
	    boolean scan = false;
	    if (filterInitDebug != null) {
	      debug = Boolean.valueOf(filterInitDebug).booleanValue();
	    }
	    if (filterInitScanJar != null) {
	      scan = Boolean.valueOf(filterInitScanJar).booleanValue();
	    }

	    Logger.getLogger("lwl").info("FilterSortListener  contextInitialized");

	    registerFilter(context, filterPackage, debug, scan);
	  }

	  public void contextDestroyed(ServletContextEvent sce)
	  {
	    Logger.getLogger("lwl").info("FilterSortListener  contextDestroyed");
	  }

	  private void registerFilter(ServletContext context, String pack, boolean debug, boolean scanjar)
	  {
	    try
	    {
	      TreeMap clases = sortedFilter(pack, debug, scanjar);
	      Iterator iter = clases.entrySet().iterator();
	      while (iter.hasNext()) {
	        Map.Entry e = (Map.Entry)iter.next();

	        Class filterclas = (Class)e.getValue();

	        if (filterclas.isAnnotationPresent(WebFilterSort.class)) {
	          WebFilterSort anno = (WebFilterSort)filterclas.getAnnotation(WebFilterSort.class);
	          String filterName = filterclas.getName();
	          if (!anno.filterName().equals("")) {
	            filterName = anno.filterName();
	          }

	          FilterRegistration.Dynamic dynamic = context.addFilter(filterName, (Filter)filterclas.newInstance());

	          EnumSet dispatcherTypes = EnumSet.noneOf(DispatcherType.class);
	          DispatcherType[] types = anno.dispatcherTypes();
	          for (DispatcherType dispatcherType : types) {
	            dispatcherTypes.add(dispatcherType);
	          }

	          String[] urlPatts = anno.urlPatterns();
	          String[] servletNames = anno.servletNames();

	          dynamic.addMappingForUrlPatterns(dispatcherTypes, anno.isMatchAfter(), urlPatts);

	          if (servletNames.length >= 1) {
	            dynamic.addMappingForServletNames(dispatcherTypes, anno.isMatchAfter(), servletNames);
	          }

	          dynamic.setAsyncSupported(anno.asyncSupported());

	          if (debug) printAnnoInfo(anno); 
	        }
	      }
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	  }

	  private void printAnnoInfo(WebFilterSort index) {
	    StringBuilder builder = new StringBuilder();

	    String[] urlPatterns = index.urlPatterns();
	    for (int i = 0; i < urlPatterns.length; i++) {
	      if (i == 0) {
	        builder.append("urlPatterns:");
	      }
	      builder.append(urlPatterns[i] + ",");
	    }
	    String[] servletNames = index.servletNames();
	    for (int i = 0; i < servletNames.length; i++) {
	      if (i == 0) {
	        builder.append(";servletNames:");
	      }
	      builder.append(servletNames[i] + ",");
	    }
	    builder.append(";value:" + index.value());
	    builder.append(";filterName:" + index.filterName());
	    builder.append(";displayName:" + index.displayName());
	    builder.append(";asyncSupported:" + index.asyncSupported());
	    builder.append(";isMatchAfter:" + index.isMatchAfter());
	    DispatcherType[] dis = index.dispatcherTypes();
	    for (int i = 0; i < dis.length; i++) {
	      if (i == 0) {
	        builder.append(";DispatcherType:");
	      }
	      builder.append(dis[i] + ",");
	    }
	    Logger.getLogger("lwl").info(builder.toString());
	  }

	  public TreeMap<Integer, Class<?>> sortedFilter(String packge, boolean debug, boolean scanjar)
	  {
	    Set cla = new PackageScanUtils().getClassesInPackage(packge, scanjar);

	    TreeMap filterMap = new TreeMap();
	    Iterator ite = cla.iterator();
	    while (ite.hasNext()) {
	      Class clas = (Class)ite.next();

	      if (clas.isAnnotationPresent(WebFilterSort.class)) {
	        WebFilterSort fiindex = (WebFilterSort)clas.getAnnotation(WebFilterSort.class);
	        Integer key = Integer.valueOf(fiindex.value());
	        if (!filterMap.containsKey(key))
	          filterMap.put(key, clas);
	        else {
	          try {
	            throw new Exception("exist the same level [ " + key + " ] of filter : " + clas.getName());
	          } catch (Exception e) {
	            e.printStackTrace();
	          }
	        }
	      }
	    }

	    if (debug) {
	      StringBuilder builder = new StringBuilder();
	      builder.append("the class of all scanned : \n");
	      Iterator ite2 = cla.iterator();
	      while (ite2.hasNext()) {
	        builder.append(ite2.next() + "\n");
	      }
	      Logger.getLogger("lwl").info(builder.toString());
	    }

	    if (debug) {
	      StringBuilder builder = new StringBuilder();
	      builder.append("the filters are registered :\n");
	      Iterator iter = filterMap.entrySet().iterator();
	      while (iter.hasNext()) {
	        Map.Entry en = (Map.Entry)iter.next();
	        builder.append(en.getKey() + " " + ((Class)en.getValue()).getName() + "\n");
	      }
	      Logger.getLogger("lwl").info(builder.toString());
	    }

	    return filterMap;
	  }

}
