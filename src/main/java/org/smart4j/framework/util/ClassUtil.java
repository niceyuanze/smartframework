package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by niceyuanze on 17-5-20.
 */
public class ClassUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);
    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();

    }

    /**
     * 加载类
     */

    public static Class<?> loadClass(String className, boolean isInitialized){
        Class<?> cls;
        try {
            cls = Class.forName(className,isInitialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class failure",e);
            throw new RuntimeException(e);
        }
       return cls;
    }
    /**
     * 获取指定包名下的所有类
     *
     */
    public static Set<Class<?>> getClassSet(String packageName)  {
        Set<Class<?>> classSet = new HashSet<>();
        Enumeration<URL> urls = null;
        try {
            urls = getClassLoader().
                    getResources(packageName.replace(".","/"));

            while(urls.hasMoreElements()){
                URL url = urls.nextElement();
                if(url != null){
                    String protocol = url.getProtocol();
                    if(protocol.equals("file")){
                        String packagePath = url.getPath().replaceAll("%20"," ");
                        addClass(classSet, packagePath, packageName);
                    }else if(protocol.equals("jar")){
                        JarURLConnection jarURLConnection = (JarURLConnection)url.openConnection();
                        url.openConnection();
                        if(jarURLConnection != null){
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if(jarFile != null){
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while(jarEntries.hasMoreElements()){
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if(jarEntryName.endsWith(".class")){
                                        String className = jarEntryName.substring(0,
                                                jarEntryName.lastIndexOf("."))
                                                .replaceAll("/",".");
                                        doAddClass(classSet,className);

                                    }
                                }
                            }
                        }
                    }
                }
            }

            //TODO
        } catch (IOException e) {
            LOGGER.error("get class set failure", e);
            throw new RuntimeException(e);
        }
        return classSet;

    }

    public static void main(String[] args) throws IOException {
//        int i = 0;
//        for(Class class1: getClassSet("org.smart4j.framework")){
//            System.out.println(class1);
//            System.out.println(++i);
//        }
//        System.out.println(getClassSet("org.smart4j.framework"));
//        System.out.println("org.smart4j.framework".replace(".","/"));
//        System.out.println(getClassLoader().getResource(""));
//        System.out.println(getClassLoader().getResource("org/smart4j/framework/helper/ConfigHelper.class").getProtocol());

//        Enumeration<URL> urls = getClassLoader().
//                getResources("org.smart4j.framework".replace(".","/"));
//        String packageName = "org.smart4j.framework";
//        while(urls.hasMoreElements()){
//            System.out.println("-----------------------------");
//            URL url = urls.nextElement();
//            System.out.println(url);
//            if(url != null) {
//                String protocol = url.getProtocol();
//                if (protocol.equals("file")) {
//                    String packagePath = url.getPath().replaceAll("%20", " ");
//                    System.out.println(packagePath);
//                    File[] files = new File(packagePath).listFiles();
//                    for(File file: files){
//
//                        String fileName = file.getName();
//
//                        if(file.isFile()){
////                            System.out.println("**********************");
////                            System.out.println(file);
////                            System.out.println(fileName);
////                            String className = fileName.substring(0, fileName.lastIndexOf("."));
////                            System.out.println(className);
////                            if(StringUtil.isNotEmpty(packageName)){
////                                className = packageName + "." + className;
////                                System.out.println(className);
////                            }
//                        }else{
//                            System.out.println("**********************");
//                            System.out.println(file);
//                            System.out.println(file.getPath());
//                            System.out.println(fileName);
//                            String subPackagePath = fileName;
//                            System.out.println(packagePath);
//                            if(StringUtil.isNotEmpty(packagePath)){
//                                subPackagePath = packagePath + "/" + subPackagePath;
//                                System.out.println("+:"+subPackagePath);
//                            }
//                            String subPackageName = fileName;
//                            System.out.println(subPackageName);
//                            if(StringUtil.isNotEmpty(packageName)){
//                                subPackageName = packageName+"."+subPackageName;
//                                System.out.println("-:"+subPackageName);
//                            }
////                            addClass(classSet,subPackagePath,subPackageName);
//                        }
//                    }
//                }
//            }
//        }
//

    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName){

        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) ||
                        file.isDirectory();
            }
        });

        for(File file: files){

            String fileName = file.getName();

            if(file.isFile()){
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if(StringUtil.isNotEmpty(packageName)){
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else{
//                String subPackagePath = fileName;
//                if(StringUtil.isNotEmpty(packagePath)){
//                    subPackagePath = packagePath + "/" + subPackagePath;
//                }
                String subPackageName = fileName;
                if(StringUtil.isNotEmpty(packageName)){
                    subPackageName = packageName+"."+subPackageName;
                }
                addClass(classSet,file.getPath(),subPackageName);
            }
        }



    }

    private static void doAddClass(Set<Class<?>> classSet, String className){
        Class<?> cls = loadClass(className,false);
        classSet.add(cls);
    }


}
