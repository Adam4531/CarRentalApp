import Big from 'big.js';

export interface SelectedCar {
    id: number;
    brand: string;
    model: string;
    engineCapacity: Big;
    bodyType: string;
    typeOfFuel: string;
    newCarCost: number;
    productionYear: number;
    status: string;
    //rentCost: Big;

    // DO ZMIANY POD NOWĄ LISTĘ KTÓRĄ ROBI ADAM
}
