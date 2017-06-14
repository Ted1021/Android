package com.study.tedkim.retrofit2;

/**
 * Created by tedkim on 2017. 6. 14..
 */

public class ParkData {
    private SearchParkInfoService SearchParkInfoService;

    public SearchParkInfoService getSearchParkInfoService () {
        return SearchParkInfoService;
    }

    public void setSearchParkInfoService (SearchParkInfoService SearchParkInfoService) {
        this.SearchParkInfoService = SearchParkInfoService;
    }

    @Override
    public String toString() {
        return "ClassPojo [SearchParkInfoService = "+SearchParkInfoService+"]";
    }

    public class SearchParkInfoService {
        private RESULT RESULT;

        private String list_total_count;

        private Row[] row;

        public RESULT getRESULT ()
        {
            return RESULT;
        }

        public void setRESULT (RESULT RESULT)
        {
            this.RESULT = RESULT;
        }

        public String getList_total_count ()
        {
            return list_total_count;
        }

        public void setList_total_count (String list_total_count)
        {
            this.list_total_count = list_total_count;
        }

        public Row[] getRow ()
        {
            return row;
        }

        public void setRow (Row[] row)
        {
            this.row = row;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [RESULT = "+RESULT+", list_total_count = "+list_total_count+", row = "+row+"]";
        }
    }

    public class Row
    {
        private String P_PARK;

        private String P_IMG;

        private String G_LONGITUDE;

        private String G_LATITUDE;

        private String P_DIVISION;

        private String LONGITUDE;

        private String P_ZONE;

        private String P_ADDR;

        private String LATITUDE;

        private String P_ADMINTEL;

        private String P_LIST_CONTENT;

        private String P_IDX;

        public String getP_PARK ()
        {
            return P_PARK;
        }

        public void setP_PARK (String P_PARK)
        {
            this.P_PARK = P_PARK;
        }

        public String getP_IMG ()
        {
            return P_IMG;
        }

        public void setP_IMG (String P_IMG)
        {
            this.P_IMG = P_IMG;
        }

        public String getG_LONGITUDE ()
        {
            return G_LONGITUDE;
        }

        public void setG_LONGITUDE (String G_LONGITUDE)
        {
            this.G_LONGITUDE = G_LONGITUDE;
        }

        public String getG_LATITUDE ()
        {
            return G_LATITUDE;
        }

        public void setG_LATITUDE (String G_LATITUDE)
        {
            this.G_LATITUDE = G_LATITUDE;
        }

        public String getP_DIVISION ()
        {
            return P_DIVISION;
        }

        public void setP_DIVISION (String P_DIVISION)
        {
            this.P_DIVISION = P_DIVISION;
        }

        public String getLONGITUDE ()
        {
            return LONGITUDE;
        }

        public void setLONGITUDE (String LONGITUDE)
        {
            this.LONGITUDE = LONGITUDE;
        }

        public String getP_ZONE ()
        {
            return P_ZONE;
        }

        public void setP_ZONE (String P_ZONE)
        {
            this.P_ZONE = P_ZONE;
        }

        public String getP_ADDR ()
        {
            return P_ADDR;
        }

        public void setP_ADDR (String P_ADDR)
        {
            this.P_ADDR = P_ADDR;
        }

        public String getLATITUDE ()
        {
            return LATITUDE;
        }

        public void setLATITUDE (String LATITUDE)
        {
            this.LATITUDE = LATITUDE;
        }

        public String getP_ADMINTEL ()
        {
            return P_ADMINTEL;
        }

        public void setP_ADMINTEL (String P_ADMINTEL)
        {
            this.P_ADMINTEL = P_ADMINTEL;
        }

        public String getP_LIST_CONTENT ()
        {
            return P_LIST_CONTENT;
        }

        public void setP_LIST_CONTENT (String P_LIST_CONTENT)
        {
            this.P_LIST_CONTENT = P_LIST_CONTENT;
        }

        public String getP_IDX ()
        {
            return P_IDX;
        }

        public void setP_IDX (String P_IDX)
        {
            this.P_IDX = P_IDX;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [P_PARK = "+P_PARK+", P_IMG = "+P_IMG+", G_LONGITUDE = "+G_LONGITUDE+", G_LATITUDE = "+G_LATITUDE+", P_DIVISION = "+P_DIVISION+", LONGITUDE = "+LONGITUDE+", P_ZONE = "+P_ZONE+", P_ADDR = "+P_ADDR+", LATITUDE = "+LATITUDE+", P_ADMINTEL = "+P_ADMINTEL+", P_LIST_CONTENT = "+P_LIST_CONTENT+", P_IDX = "+P_IDX+"]";
        }
    }

    public class RESULT
    {
        private String MESSAGE;

        private String CODE;

        public String getMESSAGE ()
        {
            return MESSAGE;
        }

        public void setMESSAGE (String MESSAGE)
        {
            this.MESSAGE = MESSAGE;
        }

        public String getCODE ()
        {
            return CODE;
        }

        public void setCODE (String CODE)
        {
            this.CODE = CODE;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [MESSAGE = "+MESSAGE+", CODE = "+CODE+"]";
        }
    }
}

