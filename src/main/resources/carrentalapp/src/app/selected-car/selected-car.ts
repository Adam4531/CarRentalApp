import Big from 'big.js';

export interface SelectedCar {
    id: number;
    brand: string;
    model: string;
    engineCapacity: Big;
    bodyTypeEnum: string;
    typeOfFuelEnum: string;
    newCarCost: number;
    productionYear: number;
    status: string;
    pricePerDayRent: Big;

    // DO ZMIANY POD NOWĄ LISTĘ KTÓRĄ ROBI ADAM
}
