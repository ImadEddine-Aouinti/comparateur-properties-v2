package ump.PropertiesComparator.model;

import java.util.Map;

public class ComparisonResult {
    private final String file1;
    private final String file2;
    private final Map<String,String> diff ;
    public ComparisonResult(String file1,String file2,Map<String ,String> diff){
        this.file1=file1;
        this.file2=file2;
        this.diff=diff;
    }
    public String getFile1(){
        return file1;
    }
    public String getFile2(){
        return file2;
    }
    public Map<String ,String> getDiff(){
        return diff;
    }

}
