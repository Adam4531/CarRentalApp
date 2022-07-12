import Big from 'big.js';
export interface Car{
    id: number;
    brand: string;
    model: string;
    engineCapacity: Big;
    bodyType: string;
    typeOfFuel: string;
    newCarCost: number;
    productionYear: number
    status: string
}
