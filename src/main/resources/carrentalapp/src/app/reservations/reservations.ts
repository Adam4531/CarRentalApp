export class ReservationDto {
    userId!: number;
    carId!: number;
    dateStart!: Date;
    dateEnd!: Date;
    cost!: number;
    paymentInAdvance!: number;
}
