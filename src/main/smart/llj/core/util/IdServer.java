package main.smart.llj.core.util;

import org.apache.commons.id.uuid.VersionFourGenerator;

/**
 * Created by Grant on 15/9/28.
 */
public class IdServer {
    public static String getIdForUUID()
    {
        return VersionFourGenerator.getInstance().nextUUID().toString();
    }
    public static void main(String args[])
    {
        System.out.println("UUID:"+getIdForUUID());
    }
}
