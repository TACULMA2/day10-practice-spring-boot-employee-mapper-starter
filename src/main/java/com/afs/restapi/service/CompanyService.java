package com.afs.restapi.service;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.exception.CompanyNotFoundException;
import com.afs.restapi.repository.CompanyRepository;
import com.afs.restapi.repository.EmployeeRepository;
import com.afs.restapi.service.dto.CompanyRequest;
import com.afs.restapi.service.dto.CompanyResponse;
import com.afs.restapi.service.mapper.CompanyMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<CompanyResponse> findAll() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(CompanyMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CompanyResponse findById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
        List<Employee> employees = employeeRepository.findAllByCompanyId(id);
        CompanyResponse response = CompanyMapper.toResponse(company);
        response.setEmployees(employees);
        response.setEmployeesLength(employees.size());
        return response;
    }

    public List<CompanyResponse> findByPage(Integer pageNumber, Integer pageSize) {
        List<Company> companiesInThePage = companyRepository.findAll(PageRequest.of(pageNumber - 1, pageSize))
                .stream()
                .collect(Collectors.toList());
        return companiesInThePage.stream()
                .map(CompanyMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void update(Long id, CompanyRequest companyRequest) {
        Company toBeUpdatedCompany = companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);
        toBeUpdatedCompany.setName(companyRequest.getName());
        companyRepository.save(toBeUpdatedCompany);
    }

    public CompanyResponse create(CompanyRequest companyRequest) {
        Company company = CompanyMapper.toEntity(companyRequest);
        return CompanyMapper.toResponse(companyRepository.save(company));
    }

    public List<Employee> findEmployeesByCompanyId(Long id) {
        return employeeRepository.findAllByCompanyId(id);
    }

    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
