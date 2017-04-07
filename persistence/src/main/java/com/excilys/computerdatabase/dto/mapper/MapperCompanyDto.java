package com.excilys.computerdatabase.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.dto.CompanyDTO;
import com.excilys.computerdatabase.dto.CompanyDTO.Builder;
import com.excilys.computerdatabase.entity.Company;

/**
 * @author jlng
 *
 * 2017-03-21
 */
public class MapperCompanyDto {

    /**
    *
    * @param companies :
    * @return a list of company
    */
   public static List<Company> fromCompaniesDtoToCompanies(List<CompanyDTO> companiesDto) {
       List<Company> companies = new ArrayList<>();
       for (CompanyDTO companyDto : companiesDto) {
    	   companies.add(fromDTO(companyDto));
       }
       return companies;
   }
   
   public static List<CompanyDTO> fromCompaniesToCompaniesDto(Iterable<Company> companies) {
       List<CompanyDTO> companiesDto = new ArrayList<>();
       for (Company company : companies) {
           companiesDto.add(toDTO(company));
       }
       return companiesDto;
   }
   
   public static Company fromDTO(CompanyDTO companyDto) {
	   Company company = new Company.Builder()
			   .withId(companyDto.getId())
			   .withName(companyDto.getName())
			   .build();
	   return company;
   }
   
   public static CompanyDTO toDTO(Company company) {
	   CompanyDTO companyDTO = new CompanyDTO.Builder()
			   .withId(company.getId())
			   .withName(company.getName())
			   .build();
	   return companyDTO;
   }
}
