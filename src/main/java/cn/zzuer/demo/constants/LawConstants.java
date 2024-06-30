package cn.zzuer.demo.constants;

//索引库的创建
public class LawConstants {
    public static final String MAPPING_TEMPLATE =
            "{\n" +
                    "  \"mappings\": {\n" +
                    "    \"properties\": {\n" +
                    "      \"caseId\": {\n" +
                    "        \"type\": \"keyword\"\n" +
                    "      },\n" +
                    "      \"plaintiff\": {\n" +
                    "        \"type\": \"keyword\"\n" +
                    "      },\n" +
                    "      \"defendant\": {\n" +
                    "        \"type\": \"keyword\"\n" +
                    "      },\n" +
                    "      \"plaintiffLawyer\": {\n" +
                    "        \"type\": \"keyword\"\n" +
                    "      },\n" +
                    "      \"defendantLawyer\": {\n" +
                    "        \"type\": \"keyword\"\n" +
                    "      },\n" +
                    "      \"caseName\": {\n" +
                    "        \"type\": \"text\",\n" +
                    "        \"analyzer\": \"ik_smart\"\n" +
                    "      },\n" +
                    "      \"court\": {\n" +
                    "        \"type\": \"keyword\"\n" +
                    "      },\n" +
                    "      \"judge\": {\n" +
                    "        \"type\": \"keyword\"\n" +
                    "      },\n" +
                    "      \"causeOfAction\": {\n" +
                    "        \"type\": \"text\",\n" +
                    "        \"analyzer\": \"ik_smart\"\n" +
                    "      },\n" +
                    "      \"judgmentResult\": {\n" +
                    "        \"type\": \"keyword\"\n" +
                    "      },\n" +
                    "      \"hiberarchy\": {\n" +
                    "        \"type\": \"keyword\"\n" +
                    "      },\n" +
                    "      \"location\": {\n" +
                    "        \"type\": \"keyword\"\n" +
                    "      },\n" +
                    "      \"text\": {\n" +
                    "        \"type\": \"text\",\n" +
                    "        \"analyzer\": \"ik_smart\"\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }\n" +
                    "}";
}
