/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */

package main.smart.llj.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Utility class to localize the modification date header-related logic.
 */
public class ModDateHeaderUtil {
    private static final Log log = LogFactory.getLog(ModDateHeaderUtil.class);
    
    private static final long expiresOffset = 3600 * 24 * 10;

    // Utility class with static methods; inhibit construction
    private ModDateHeaderUtil() {

    }

    /**
     * Sets the HTTP response status to 304 (NOT MODIFIED) if the request contains an
     * If-Modified-Since header that specifies a time that is
     * at or after the time specified by the value of lastModifiedTimeMillis
     * <em>truncated to second granularity</em>.  Returns true if
     * the response status was set, false if not.
     *
     * @param request
     * @param response
     * @param lastModifiedTimeMillis
     * @return true if a response status was sent, false otherwise.
     */
    public static boolean respondIfNotModified(HttpServletRequest request,
                                               HttpServletResponse response,
                                               long lastModifiedTimeMillis) {
        long sinceDate = request.getDateHeader("If-Modified-Since");
        // truncate to seconds
        lastModifiedTimeMillis -= (lastModifiedTimeMillis % 1000);
        log.debug("since date = " + sinceDate);
        log.debug("last mod date (trucated to seconds) = " + lastModifiedTimeMillis);
        if (lastModifiedTimeMillis <= sinceDate) {
            log.debug("NOT MODIFIED " + request.getRequestURL());
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set the Last-Modified header using the given time in milliseconds.  Note that because the
     * header has the granularity of one second, the value will get truncated to the nearest second that does not
     * exceed the provided value.
     * <p/>
     * This will also set the Expires header to a date in the past.  This forces clients to revalidate the cache each
     * time.
     *
     * @param response
     * @param lastModifiedTimeMillis
     */
    public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedTimeMillis) {
        response.setDateHeader("Last-Modified", lastModifiedTimeMillis);
        // Force clients to revalidate each time
        // See RFC 2616 (HTTP 1.1 spec) secs 14.21, 13.2.1
        response.setDateHeader("Expires", 0);
        // We may also want this (See 13.2.1 and 14.9.4)
        // response.setHeader("Cache-Control","must-revalidate");
    }
    /**
     * 判断1天为过期时间
     * @param request
     * @param response
     * @return
     */
    public static boolean respondIfNotModifiedOneDay(HttpServletRequest request,
	            HttpServletResponse response) {
		long sinceDate = request.getDateHeader("If-Modified-Since");
		// truncate to seconds
		long lastModifiedTimeMillis = System.currentTimeMillis() - 24*60*60*1000;
		log.debug("since date = " + sinceDate);
		log.debug("last mod date (trucated to seconds) = " + lastModifiedTimeMillis);
		if (lastModifiedTimeMillis <= sinceDate) {
		log.debug("NOT MODIFIED " + request.getRequestURL());
		//response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
		return true;
		} else {
		return false;
		}
	}
    /**
     * 设置最后访问时间为当前时间
     * @param response
     */
    public static void setLastModifiedHeader(HttpServletResponse response) {
    	SimpleDateFormat df = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss");
        Calendar lastModifiedCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        String lastModified = df.format(lastModifiedCal.getTime());
    	
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        response.setHeader("Date",df.format(cal.getTime())+" GMT");
        cal.add(Calendar.DAY_OF_MONTH,1);
       // response.setHeader("Expires",df.format(cal.getTime())+" GMT");
        response.setHeader("Retry-After",df.format(cal.getTime())+" GMT");
        response.setHeader("Cache-Control","public");
        //response.setHeader("Last-Modified",lastModified+" GMT");

        response.setDateHeader("Last-Modified", System.currentTimeMillis()-60*60*1000);
        // Force clients to revalidate each time
        // See RFC 2616 (HTTP 1.1 spec) secs 14.21, 13.2.1
        response.setDateHeader("Expires", System.currentTimeMillis() + (expiresOffset * 1000));
        // We may also want this (See 13.2.1 and 14.9.4)
        // response.setHeader("Cache-Control","must-revalidate");
    }
}
