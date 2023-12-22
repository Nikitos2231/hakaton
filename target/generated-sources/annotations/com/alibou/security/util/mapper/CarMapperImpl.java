package com.alibou.security.util.mapper;

import com.alibou.security.model.entity.Car;
import com.alibou.security.rest.dto.CarDto;
import com.alibou.security.rest.dto.EntitiesTableResult;
import com.alibou.security.rest.dto.ShortCarDto;
import com.alibou.security.rest.dto.request.EditCarRequest;
import com.alibou.security.rest.dto.response.EntitiesTableResultDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-22T23:46:19+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Eclipse Adoptium)"
)
@Component
public class CarMapperImpl implements CarMapper {

    @Override
    public CarDto map(Car car) {
        if ( car == null ) {
            return null;
        }

        CarDto.CarDtoBuilder carDto = CarDto.builder();

        carDto.serialNumber( car.getSerialNumber() );
        carDto.techModel( car.getTechModel() );
        carDto.engineModel( car.getEngineModel() );
        carDto.engineSerialNumber( car.getEngineSerialNumber() );
        carDto.transModel( car.getTransModel() );
        carDto.transSerialNumber( car.getTransSerialNumber() );
        carDto.driveAxleModel( car.getDriveAxleModel() );
        carDto.driveAxleSerialNumber( car.getDriveAxleSerialNumber() );
        carDto.controlBridgeModel( car.getControlBridgeModel() );
        carDto.controlBridgeSerialNumber( car.getControlBridgeSerialNumber() );
        carDto.factoryDateShipment( car.getFactoryDateShipment() );
        carDto.client( car.getClient() );
        carDto.consumer( car.getConsumer() );
        carDto.deliveryAddress( car.getDeliveryAddress() );
        carDto.equipment( car.getEquipment() );
        carDto.company( car.getCompany() );

        return carDto.build();
    }

    @Override
    public Car map(EditCarRequest car) {
        if ( car == null ) {
            return null;
        }

        Car.CarBuilder car1 = Car.builder();

        car1.serialNumber( car.getSerialNumber() );
        car1.techModel( car.getTechModel() );
        car1.engineModel( car.getEngineModel() );
        car1.engineSerialNumber( car.getEngineSerialNumber() );
        car1.transModel( car.getTransModel() );
        car1.transSerialNumber( car.getTransSerialNumber() );
        car1.driveAxleModel( car.getDriveAxleModel() );
        car1.driveAxleSerialNumber( car.getDriveAxleSerialNumber() );
        car1.controlBridgeModel( car.getControlBridgeModel() );
        car1.controlBridgeSerialNumber( car.getControlBridgeSerialNumber() );
        car1.factoryDateShipment( car.getFactoryDateShipment() );
        car1.client( car.getClient() );
        car1.consumer( car.getConsumer() );
        car1.deliveryAddress( car.getDeliveryAddress() );
        car1.equipment( car.getEquipment() );
        car1.company( car.getCompany() );

        return car1.build();
    }

    @Override
    public CarDto mapToCarDto(EditCarRequest car) {
        if ( car == null ) {
            return null;
        }

        CarDto.CarDtoBuilder carDto = CarDto.builder();

        carDto.serialNumber( car.getSerialNumber() );
        carDto.techModel( car.getTechModel() );
        carDto.engineModel( car.getEngineModel() );
        carDto.engineSerialNumber( car.getEngineSerialNumber() );
        carDto.transModel( car.getTransModel() );
        carDto.transSerialNumber( car.getTransSerialNumber() );
        carDto.driveAxleModel( car.getDriveAxleModel() );
        carDto.driveAxleSerialNumber( car.getDriveAxleSerialNumber() );
        carDto.controlBridgeModel( car.getControlBridgeModel() );
        carDto.controlBridgeSerialNumber( car.getControlBridgeSerialNumber() );
        carDto.factoryDateShipment( car.getFactoryDateShipment() );
        carDto.client( car.getClient() );
        carDto.consumer( car.getConsumer() );
        carDto.deliveryAddress( car.getDeliveryAddress() );
        carDto.equipment( car.getEquipment() );
        carDto.company( car.getCompany() );

        return carDto.build();
    }

    @Override
    public ShortCarDto mapToShortCar(Car car) {
        if ( car == null ) {
            return null;
        }

        ShortCarDto.ShortCarDtoBuilder shortCarDto = ShortCarDto.builder();

        shortCarDto.serialNumber( car.getSerialNumber() );
        shortCarDto.techModel( car.getTechModel() );
        shortCarDto.engineModel( car.getEngineModel() );
        shortCarDto.engineSerialNumber( car.getEngineSerialNumber() );
        shortCarDto.transModel( car.getTransModel() );
        shortCarDto.transSerialNumber( car.getTransSerialNumber() );
        shortCarDto.driveAxleModel( car.getDriveAxleModel() );
        shortCarDto.driveAxleSerialNumber( car.getDriveAxleSerialNumber() );
        shortCarDto.controlBridgeModel( car.getControlBridgeModel() );
        shortCarDto.controlBridgeSerialNumber( car.getControlBridgeSerialNumber() );

        return shortCarDto.build();
    }

    @Override
    public EntitiesTableResultDto<CarDto> map(EntitiesTableResult<Car> techInspectionsList) {
        if ( techInspectionsList == null ) {
            return null;
        }

        EntitiesTableResultDto<CarDto> entitiesTableResultDto = new EntitiesTableResultDto<CarDto>();

        entitiesTableResultDto.setEntities( carListToCarDtoList( techInspectionsList.getEntities() ) );
        entitiesTableResultDto.setTotalPages( techInspectionsList.getTotalPages() );
        entitiesTableResultDto.setTotalElements( techInspectionsList.getTotalElements() );

        return entitiesTableResultDto;
    }

    @Override
    public Car map(CarDto car) {
        if ( car == null ) {
            return null;
        }

        Car.CarBuilder car1 = Car.builder();

        car1.serialNumber( car.getSerialNumber() );
        car1.techModel( car.getTechModel() );
        car1.engineModel( car.getEngineModel() );
        car1.engineSerialNumber( car.getEngineSerialNumber() );
        car1.transModel( car.getTransModel() );
        car1.transSerialNumber( car.getTransSerialNumber() );
        car1.driveAxleModel( car.getDriveAxleModel() );
        car1.driveAxleSerialNumber( car.getDriveAxleSerialNumber() );
        car1.controlBridgeModel( car.getControlBridgeModel() );
        car1.controlBridgeSerialNumber( car.getControlBridgeSerialNumber() );
        car1.factoryDateShipment( car.getFactoryDateShipment() );
        car1.client( car.getClient() );
        car1.consumer( car.getConsumer() );
        car1.deliveryAddress( car.getDeliveryAddress() );
        car1.equipment( car.getEquipment() );
        car1.company( car.getCompany() );

        return car1.build();
    }

    protected List<CarDto> carListToCarDtoList(List<Car> list) {
        if ( list == null ) {
            return null;
        }

        List<CarDto> list1 = new ArrayList<CarDto>( list.size() );
        for ( Car car : list ) {
            list1.add( map( car ) );
        }

        return list1;
    }
}
