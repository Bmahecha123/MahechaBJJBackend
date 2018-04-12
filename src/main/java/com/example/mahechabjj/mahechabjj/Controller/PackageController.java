package com.example.mahechabjj.mahechabjj.Controller;

import com.example.mahechabjj.mahechabjj.Model.Package;
import com.example.mahechabjj.mahechabjj.Repository.PackageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackageController {

  private PackageRepository packageRepository;

  public PackageController(PackageRepository packageRepository) {
    this.packageRepository = packageRepository;
  }

  @CrossOrigin
  @GetMapping("packages")
  public void getPackages() {

  }

  @CrossOrigin
  @PostMapping("packages")
  public ResponseEntity postPackages(@RequestBody Package newPackage) {
    this.packageRepository.save(newPackage);

    return new ResponseEntity(HttpStatus.CREATED);
  }
}
