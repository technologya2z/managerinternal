package vn.com.pvcombank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import liquibase.pro.packaged.S;
import org.springframework.stereotype.Service;
import vn.com.pvcombank.service.dto.JsonSwaggerDTO;

@Service
public class ReadJsonSwagger {

    public List<JsonSwaggerDTO> readDynamicJson(String content) throws JsonProcessingException {
        List<JsonSwaggerDTO> listswagger = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(content);

        JsonNode nodepath = getNode(jsonNode, "paths");
        List<String> listKey = getlistKey(nodepath);
        for (String path : listKey) {
            System.out.println("path: " + path); // path

            String method = nodepath.get(path).fieldNames().next(); // method
            System.out.println("method: " + method);

            JsonNode Apiname = nodepath.get(path);
            String sumary = getnodeApipath(Apiname); // tên APi
            System.out.println("tên API: " + sumary);

            listswagger.add(new JsonSwaggerDTO(path, method, sumary));
        }
        return listswagger;
    }

    public String getnodeApipath(JsonNode Apiname) {
        JsonNode nodeGetSummary = getNode(Apiname, "get");
        JsonNode nodePostSummary = getNode(Apiname, "post");
        JsonNode nodePutSummary = getNode(Apiname, "put");
        JsonNode nodeDeleteSummary = getNode(Apiname, "delete");
        if (nodeGetSummary != null) {
            String stringsummary = nodeGetSummary.get("summary").asText();
            return stringsummary;
        }
        if (nodePostSummary != null) {
            String stringsummary = nodePostSummary.get("summary").asText();
            return stringsummary;
        }
        if (nodePutSummary != null) {
            String stringsummary = nodePostSummary.get("summary").asText();
            return stringsummary;
        }
        if (nodeDeleteSummary != null) {
            String stringsummary = nodePostSummary.get("summary").asText();
            return stringsummary;
        }
        return "";
    }

    public List<String> getlistKey(JsonNode node) {
        List<String> keys = new ArrayList<>();
        Iterator<String> iterator = node.fieldNames();
        iterator.forEachRemaining(e -> keys.add(e));
        return keys;
    }

    public JsonNode getNode(JsonNode node, String key) {
        if (node.has(key)) {
            JsonNode value = node.get(key);
            return value;
        } else {
            return null;
        }
    }
    //    public static void main(String[] args) {
    //        ReadDynamicKey readDynamicKey = new ReadDynamicKey();
    //        try {
    //            readDynamicKey.readDynamicJson("");
    //        } catch (JsonProcessingException e) {
    //            e.printStackTrace();
    //        }
    //    }
    //
    //    public static String content = new String("{\n" +
    //        "  \"swagger\": \"2.0\",\n" +
    //        "  \"info\": {\n" +
    //        "    \"description\": \"smartform API documentation\",\n" +
    //        "    \"version\": \"0.0.1\",\n" +
    //        "    \"title\": \"smartform API\",\n" +
    //        "    \"contact\": {\n" +
    //        "\n" +
    //        "    },\n" +
    //        "    \"license\": {\n" +
    //        "\n" +
    //        "    }\n" +
    //        "  },\n" +
    //        "  \"host\": \"smartform-ccstk.uat.pvcb.vn\",\n" +
    //        "  \"basePath\": \"/\",\n" +
    //        "  \"tags\": [\n" +
    //        "    {\n" +
    //        "      \"name\": \"api-controller\",\n" +
    //        "      \"description\": \"Api Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"auth-controller\",\n" +
    //        "      \"description\": \"Auth Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"branch-controller\",\n" +
    //        "      \"description\": \"Branch Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"business-role-controller\",\n" +
    //        "      \"description\": \"Business Role Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"combo-controller\",\n" +
    //        "      \"description\": \"Combo Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"config-controller\",\n" +
    //        "      \"description\": \"Config Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"employee-controller\",\n" +
    //        "      \"description\": \"Employee Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"employee-group-controller\",\n" +
    //        "      \"description\": \"Employee Group Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-acc-data-req-controller\",\n" +
    //        "      \"description\": \"Ers Acc Data Req Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-api-partner-mapping-controller\",\n" +
    //        "      \"description\": \"Ers Api Partner Mapping Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-bil-account-controller\",\n" +
    //        "      \"description\": \"Ers Bil Account Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-bil-account-his-controller\",\n" +
    //        "      \"description\": \"Ers Bil Account His Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-bil-account-reg-controller\",\n" +
    //        "      \"description\": \"Ers Bil Account Reg Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-bil-customer-profile-controller\",\n" +
    //        "      \"description\": \"Ers Bil Customer Profile Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-bil-payment-controller\",\n" +
    //        "      \"description\": \"Ers Bil Payment Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-bil-payment-his-controller\",\n" +
    //        "      \"description\": \"Ers Bil Payment His Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-bil-payment-req-controller\",\n" +
    //        "      \"description\": \"Ers Bil Payment Req Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-bil-services-controller\",\n" +
    //        "      \"description\": \"Ers Bil Services Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-bil-services-his-controller\",\n" +
    //        "      \"description\": \"Ers Bil Services His Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-bil-services-req-controller\",\n" +
    //        "      \"description\": \"Ers Bil Services Req Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-cif-data-req-controller\",\n" +
    //        "      \"description\": \"Ers Cif Data Req Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-cif-document-image-controller\",\n" +
    //        "      \"description\": \"Ers Cif Document Image Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-customer-profile-combo-controller\",\n" +
    //        "      \"description\": \"Ers Customer Profile Combo Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-customer-profile-combo-req-controller\",\n" +
    //        "      \"description\": \"Ers Customer Profile Combo Req Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-customer-profile-controller\",\n" +
    //        "      \"description\": \"Ers Customer Profile Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-customer-profile-his-controller\",\n" +
    //        "      \"description\": \"Ers Customer Profile His Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-customer-profile-req-controller\",\n" +
    //        "      \"description\": \"Ers Customer Profile Req Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-data-mapping-controller\",\n" +
    //        "      \"description\": \"Ers Data Mapping Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-discount-controller\",\n" +
    //        "      \"description\": \"Ers Discount Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-ecom-services-controller\",\n" +
    //        "      \"description\": \"Ers Ecom Services Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-ecom-services-req-controller\",\n" +
    //        "      \"description\": \"Ers Ecom Services Req Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-ib-approval-matrix-his-resource\",\n" +
    //        "      \"description\": \"Ers Ib Approval Matrix His Resource\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-ib-approval-matrix-req-resource\",\n" +
    //        "      \"description\": \"Ers Ib Approval Matrix Req Resource\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-ib-approval-service-his-resource\",\n" +
    //        "      \"description\": \"Ers Ib Approval Service His Resource\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-ib-approval-service-req-resource\",\n" +
    //        "      \"description\": \"Ers Ib Approval Service Req Resource\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-ib-approval-type-his-resource\",\n" +
    //        "      \"description\": \"Ers Ib Approval Type His Resource\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-ib-approval-type-req-resource\",\n" +
    //        "      \"description\": \"Ers Ib Approval Type Req Resource\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-ib-service-code-matrix-controller\",\n" +
    //        "      \"description\": \"Ers IB Service Code Matrix Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-ib-service-req-controller\",\n" +
    //        "      \"description\": \"Ers IB Service Req Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-ib-services-his-controller\",\n" +
    //        "      \"description\": \"Ers IB Services His Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-ib-services-tmp-controller\",\n" +
    //        "      \"description\": \"Ers IB Services Tmp Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-keycloak-mapping-controller\",\n" +
    //        "      \"description\": \"Ers Keycloak Mapping Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-ocr-data-controller\",\n" +
    //        "      \"description\": \"Ers Ocr Data Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-provider-config-controller\",\n" +
    //        "      \"description\": \"Ers Provider Config Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-setting-controller\",\n" +
    //        "      \"description\": \"Ers Setting Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-signature-history-controller\",\n" +
    //        "      \"description\": \"Ers Signature History Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-sms-account-his-controller\",\n" +
    //        "      \"description\": \"Ers Sms Account His Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-sms-account-req-controller\",\n" +
    //        "      \"description\": \"Ers Sms Account Req Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-sms-account-tmp-controller\",\n" +
    //        "      \"description\": \"Ers Sms Account Tmp Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-sms-sender-his-controller\",\n" +
    //        "      \"description\": \"Ers Sms Sender His Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-sms-sender-req-controller\",\n" +
    //        "      \"description\": \"Ers Sms Sender Req Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-sms-sender-tmp-controller\",\n" +
    //        "      \"description\": \"Ers Sms Sender Tmp Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-sms-services-his-controller\",\n" +
    //        "      \"description\": \"Ers Sms Services His Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-sms-services-req-controller\",\n" +
    //        "      \"description\": \"Ers Sms Services Req Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-sms-services-tmp-controller\",\n" +
    //        "      \"description\": \"Ers Sms Services Tmp Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-sync-phone-controller\",\n" +
    //        "      \"description\": \"Ers Sync Phone Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-top-account-controller\",\n" +
    //        "      \"description\": \"Ers Top Account Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-top-account-his-controller\",\n" +
    //        "      \"description\": \"Ers Top Account His Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-top-account-req-controller\",\n" +
    //        "      \"description\": \"Ers Top Account Req Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-top-customer-profile-controller\",\n" +
    //        "      \"description\": \"Ers Top Customer Profile Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-top-payment-controller\",\n" +
    //        "      \"description\": \"Ers Top Payment Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-top-payment-his-controller\",\n" +
    //        "      \"description\": \"Ers Top Payment His Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-top-payment-reg-controller\",\n" +
    //        "      \"description\": \"Ers Top Payment Reg Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-top-value-controller\",\n" +
    //        "      \"description\": \"Ers Top Value Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-top-value-his-controller\",\n" +
    //        "      \"description\": \"Ers Top Value His Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"ers-top-value-reg-controller\",\n" +
    //        "      \"description\": \"Ers Top Value Reg Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"esb-controller\",\n" +
    //        "      \"description\": \"Esb Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"form-control-controller\",\n" +
    //        "      \"description\": \"Form Control Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"loan-contract-controller\",\n" +
    //        "      \"description\": \"Loan Contract Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"loan-contract-details-resource\",\n" +
    //        "      \"description\": \"Loan Contract Details Resource\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"role-group-controller\",\n" +
    //        "      \"description\": \"Role Group Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"roles-controller\",\n" +
    //        "      \"description\": \"Roles Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"service-controller\",\n" +
    //        "      \"description\": \"Service Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"service-group-controller\",\n" +
    //        "      \"description\": \"Service Group Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"sms-banking-controller\",\n" +
    //        "      \"description\": \"Sms Banking Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"system-log-controller\",\n" +
    //        "      \"description\": \"System Log Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"transaction-controller\",\n" +
    //        "      \"description\": \"Transaction Controller\"\n" +
    //        "    },\n" +
    //        "    {\n" +
    //        "      \"name\": \"transaction-value-controller\",\n" +
    //        "      \"description\": \"Transaction Value Controller\"\n" +
    //        "    }\n" +
    //        "  ],\n" +
    //        "  \"paths\": {\n" +
    //        "    \"/api/account\": {\n" +
    //        "      \"get\": {\n" +
    //        "        \"tags\": [\n" +
    //        "          \"auth-controller\"\n" +
    //        "        ],\n" +
    //        "        \"summary\": \"getCurrentUser\",\n" +
    //        "        \"operationId\": \"getCurrentUserUsingGET\",\n" +
    //        "        \"produces\": [\n" +
    //        "          \"*/*\"\n" +
    //        "        ],\n" +
    //        "        \"responses\": {\n" +
    //        "          \"200\": {\n" +
    //        "            \"description\": \"OK\",\n" +
    //        "            \"schema\": {\n" +
    //        "              \"$ref\": \"#/definitions/EmployeeDTO\"\n" +
    //        "            }\n" +
    //        "          },\n" +
    //        "          \"401\": {\n" +
    //        "            \"description\": \"Unauthorized\"\n" +
    //        "          },\n" +
    //        "          \"403\": {\n" +
    //        "            \"description\": \"Forbidden\"\n" +
    //        "          },\n" +
    //        "          \"404\": {\n" +
    //        "            \"description\": \"Not Found\"\n" +
    //        "          }\n" +
    //        "        },\n" +
    //        "        \"deprecated\": false\n" +
    //        "      }\n" +
    //        "    },\n" +
    //        "    \"/api/api-partner-mappings\": {\n" +
    //        "      \"post\": {\n" +
    //        "        \"tags\": [\n" +
    //        "          \"ers-api-partner-mapping-controller\"\n" +
    //        "        ],\n" +
    //        "        \"summary\": \"getApiPartnerData\",\n" +
    //        "        \"operationId\": \"getApiPartnerDataUsingPOST\",\n" +
    //        "        \"consumes\": [\n" +
    //        "          \"application/json\"\n" +
    //        "        ],\n" +
    //        "        \"produces\": [\n" +
    //        "          \"*/*\"\n" +
    //        "        ],\n" +
    //        "        \"parameters\": [\n" +
    //        "          {\n" +
    //        "            \"name\": \"httpEntity\",\n" +
    //        "            \"in\": \"query\",\n" +
    //        "            \"description\": \"httpEntity\",\n" +
    //        "            \"required\": false,\n" +
    //        "            \"type\": \"string\"\n" +
    //        "          }\n" +
    //        "        ],\n" +
    //        "        \"responses\": {\n" +
    //        "          \"200\": {\n" +
    //        "            \"description\": \"OK\",\n" +
    //        "            \"schema\": {\n" +
    //        "              \"$ref\": \"#/definitions/ApiPartnerDataDTO\"\n" +
    //        "            }\n" +
    //        "          },\n" +
    //        "          \"201\": {\n" +
    //        "            \"description\": \"Created\"\n" +
    //        "          },\n" +
    //        "          \"401\": {\n" +
    //        "            \"description\": \"Unauthorized\"\n" +
    //        "          },\n" +
    //        "          \"403\": {\n" +
    //        "            \"description\": \"Forbidden\"\n" +
    //        "          },\n" +
    //        "          \"404\": {\n" +
    //        "            \"description\": \"Not Found\"\n" +
    //        "          }\n" +
    //        "        },\n" +
    //        "        \"deprecated\": false\n" +
    //        "      }\n" +
    //        "    }\n" +
    //        "  },\n" +
    //        "  \"definitions\": {\n" +
    //        "    \"UserProfileDTO\": {\n" +
    //        "      \"type\": \"object\",\n" +
    //        "      \"properties\": {\n" +
    //        "        \"address\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"branch\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"cbnv\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"cellPhone\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"cifNo\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"companyPhone\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"contPosition\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"contactFullName\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"contactGender\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"contactIdDate\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"contactIdNumber\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"contactIdPlace\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"contactIdType\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"contperson\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"country\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"customerType\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"dateOfBirth\": {\n" +
    //        "          \"type\": \"string\",\n" +
    //        "          \"format\": \"date\"\n" +
    //        "        },\n" +
    //        "        \"email\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"exIdDate\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"faxNumber\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"fullName\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"gender\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"homePhone\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"idDate\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"idNumber\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"idPlace\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"idType\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"marital\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"mobileAuthorize\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"mobileDefinition\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"signature\": {\n" +
    //        "          \"type\": \"string\",\n" +
    //        "          \"format\": \"byte\"\n" +
    //        "        },\n" +
    //        "        \"specialField7\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"specialField8\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"target\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        },\n" +
    //        "        \"taxCode\": {\n" +
    //        "          \"type\": \"string\"\n" +
    //        "        }\n" +
    //        "      },\n" +
    //        "      \"title\": \"UserProfileDTO\"\n" +
    //        "    }\n" +
    //        "  }\n" +
    //        "}");

}
